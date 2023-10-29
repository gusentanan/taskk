package com.bagusmerta.taskk.presentation.screen.reminder.data

import com.bagusmerta.taskk.data.local.LocalDataSource
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.extensions.getNextScheduledDate
import com.bagusmerta.taskk.utils.extensions.toggleStatusHandler
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import javax.inject.Inject

class ReminderEnvironmentImpl @Inject constructor(
    private val dateTimeProvider: DateTimeProvider,
    private val localDataSource: LocalDataSource,
    private val alarmManager: AlarmManager,
    private val notifyManager: NotifyManager
): IReminderEnvironment {
    override fun activateNotification(taskkId: String): Flow<TaskkToDo> {
        return getTaskk(taskkId)
            .onEach { tasks ->
               notifyManager.show(tasks)
            }
    }

    override fun snoozeAlarm(taskkId: String): Flow<TaskkToDo> {
        return getTaskk(taskkId)
            .onEach { tasks ->
                alarmManager.scheduleTaskkAlarm(tasks, dateTimeProvider.getNowDate().plusMinutes(10))
                notifyManager.dismiss(tasks)
            }
    }

    override fun finishAlarm(taskkId: String): Flow<TaskkToDo> {
        return getTaskk(taskkId)
            .onEach { tasks ->
                val currentDate = dateTimeProvider.getNowDate()
                tasks.toggleStatusHandler(
                    currentDate,
                    { _, newStatus ->
                        localDataSource.updateTaskkStatus(tasks.id, newStatus, currentDate)
                    },
                    { nextDueDate ->
                        localDataSource.updateTaskkDueDate(tasks.id, nextDueDate, currentDate, tasks.isDueDateTimeSet)
                    }
                )
                alarmManager.cancelTaskkAlarm(tasks)
                notifyManager.dismiss(tasks)
            }
    }

    override fun restartAll(): Flow<List<TaskkToDo>> {
        return localDataSource.getScheduledTaskk()
            .take(1)
            .onEach { tasks ->
                tasks.forEach {
                    alarmManager.scheduleTaskkAlarm(it, it.getNextScheduledDate(dateTimeProvider.getNowDate()))
                }
            }
    }

    private fun getTaskk(taskkId: String): Flow<TaskkToDo> {
        return localDataSource.getTaskkById(taskkId)
            .take(1)
            .filter { items -> items.status != TaskkStatus.COMPLETE && items.dueDate != null }

    }
}