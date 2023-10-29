package com.bagusmerta.taskk.presentation.screen.reminder.ui

import com.bagusmerta.taskk.presentation.screen.reminder.data.IReminderEnvironment
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(DelicateCoroutinesApi::class)
@Singleton
class ReminderViewModel @Inject constructor(
    private val reminderEnvironment: IReminderEnvironment
) {

    fun dispatch(event: ReminderEvent) {
        when(event){
            is ReminderEvent.ShowAlarm -> {
                GlobalScope.launch {
                    reminderEnvironment.activateNotification(event.taskkId).collect()
                }
            }
            is ReminderEvent.NotificationSnooze -> {
                GlobalScope.launch {
                    reminderEnvironment.snoozeAlarm(event.taskkId).collect()
                }
            }
            is ReminderEvent.NotificationDone -> {
                GlobalScope.launch {
                    reminderEnvironment.finishAlarm(event.taskkId).collect()
                }
            }
            is ReminderEvent.ApplicationBootCompleted -> {
                GlobalScope.launch {
                    reminderEnvironment.restartAll().collect()
                }
            }
        }
    }
}