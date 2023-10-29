package com.bagusmerta.taskk.presentation.screen.reminder.data

import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkToDo
import kotlinx.coroutines.flow.Flow

interface IReminderEnvironment {
    fun activateNotification(taskkId: String): Flow<TaskkToDo>
    fun snoozeAlarm(taskkId: String): Flow<TaskkToDo>
    fun finishAlarm(taskkId: String): Flow<TaskkToDo>
    fun restartAll(): Flow<List<TaskkToDo>>
}