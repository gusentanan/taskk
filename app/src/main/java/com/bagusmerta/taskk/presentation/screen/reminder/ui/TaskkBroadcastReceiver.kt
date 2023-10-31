package com.bagusmerta.taskk.presentation.screen.reminder.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TaskkBroadcastReceiver: BroadcastReceiver() {

    @Inject
    lateinit var reminderViewModel: ReminderViewModel

    override fun onReceive(context: Context?, intent: Intent?) {

        when(intent?.action){
            EVENT_NOTIFY_DONE -> {
                reminderViewModel.dispatch(ReminderEvent.NotificationDone(getTaskkId(intent)))
            }
            EVENT_NOTIFY_SNOOZE -> {
                reminderViewModel.dispatch(ReminderEvent.NotificationSnooze(getTaskkId(intent)))
            }
            EVENT_SHOW_ALARM -> {
                reminderViewModel.dispatch(ReminderEvent.ShowAlarm(getTaskkId(intent)))
            }
            Intent.ACTION_BOOT_COMPLETED -> {
                reminderViewModel.dispatch(ReminderEvent.ApplicationBootCompleted)
            }
        }
    }

    private fun getTaskkId(intent: Intent?): String {
        return intent?.getStringExtra(TASKK_ID) ?: ""
    }

    companion object {
        const val TASKK_ID = "com.bagusmerta.taskk.intent.extra.TASKK_ID"

        const val EVENT_SHOW_ALARM = "com.bagusmerta.taskk.intent.action.SHOW_ALARM"
        const val EVENT_NOTIFY_DONE = "com.bagusmerta.taskk.intent.action.NOTIFY_DONE"
        const val EVENT_NOTIFY_SNOOZE = "com.bagusmerta.taskk.intent.action.NOTIFY_SNOOZE"
    }
}