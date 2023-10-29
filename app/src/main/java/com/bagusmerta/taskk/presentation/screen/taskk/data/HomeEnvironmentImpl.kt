package com.bagusmerta.taskk.presentation.screen.taskk.data

import com.bagusmerta.taskk.data.local.LocalDataSource
import com.bagusmerta.taskk.domain.model.TaskkDiff
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.presentation.screen.reminder.data.AlarmManager
import com.bagusmerta.taskk.presentation.screen.reminder.data.NotifyManager
import com.bagusmerta.taskk.utils.extensions.getNextScheduledDate
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class HomeEnvironmentImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    override val dateTimeProvider: DateTimeProvider,
    private val alarmManager: AlarmManager,
    private val notifyManager: NotifyManager
): IHomeEnvironment {

    override fun getListTaskk(listId: String): Flow<TaskkList> {
        return localDataSource.getListTaskk().map {
            TaskkList(
                id = "1", // Set as 1 since for now we only expect one list of a task
                tasks = it
            )
        }
    }

    override fun getOverallCountTaskk(): Flow<TaskkOverallCount> {
        return localDataSource.getOverallCountTaskk()
    }

    override suspend fun toggleTaskStatus(task: TaskkToDo) {
        val taskkStatus = when(task.status){
            TaskkStatus.IN_PROGRESS -> { TaskkStatus.COMPLETE }
            TaskkStatus.COMPLETE -> { TaskkStatus.IN_PROGRESS }
        }
        localDataSource.updateTaskkStatus(task.id, taskkStatus, dateTimeProvider.getNowDate())
    }


    override suspend fun deleteTask(task: TaskkToDo) {
        localDataSource.deleteTaskkById(task.id)
    }

    override fun listenTaskkDiff(): Flow<TaskkDiff> {
        var tasks: Map<String, TaskkToDo> = mapOf()
        return localDataSource.getScheduledTaskk()
            .distinctUntilChangedBy { newTasks -> newTasks.map { Pair(it.dueDate, it.status) } }
            .map { newTasks -> newTasks.associateBy({ it.id }, { it }) }
            .map { newTasks ->
                TaskkDiff(
                    addedTaskk = newTasks - tasks.keys,
                    deletedTaskk = tasks - newTasks.keys,
                    modifiedTaskk = newTasks.filter { (key, value) -> key in tasks.keys && value != tasks[key] }
                ).apply { tasks = newTasks }

            }
            .drop(1) // Skip initial value
            .onEach { todoTaskDiff ->
                todoTaskDiff.addedTaskk.forEach {
                    alarmManager.scheduleTaskkAlarm(it.value, it.value.getNextScheduledDate(dateTimeProvider.getNowDate()))
                }

                todoTaskDiff.modifiedTaskk.forEach {
                    alarmManager.scheduleTaskkAlarm(it.value, it.value.getNextScheduledDate(dateTimeProvider.getNowDate()))
                }

                todoTaskDiff.deletedTaskk.forEach {
                    alarmManager.cancelTaskkAlarm(it.value)
                    notifyManager.dismiss(it.value)
                }
            }
    }

}