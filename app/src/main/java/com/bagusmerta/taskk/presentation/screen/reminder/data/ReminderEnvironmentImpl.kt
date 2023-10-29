package com.bagusmerta.taskk.presentation.screen.reminder.data

import com.bagusmerta.taskk.domain.model.TaskkList
import kotlinx.coroutines.flow.Flow

class ReminderEnvironmentImpl: IReminderEnvironment {
    override fun activateNotification(taskkId: String): Flow<TaskkList> {
        TODO("Not yet implemented")
    }

    override fun snoozeAlarm(taskkId: String): Flow<TaskkList> {
        TODO("Not yet implemented")
    }

    override fun finishAlarm(taskkId: String): Flow<TaskkList> {
        TODO("Not yet implemented")
    }

    override fun restartAll(): Flow<TaskkList> {
        TODO("Not yet implemented")
    }
}