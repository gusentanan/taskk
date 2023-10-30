package com.bagusmerta.taskk.presentation.screen.reminder.ui

sealed class ReminderEvent {
    data class ShowAlarm(val taskkId: String): ReminderEvent()
    data class NotificationDone(val taskkId: String): ReminderEvent()
    data class NotificationSnooze(val taskkId: String): ReminderEvent()
    object ApplicationBootCompleted: ReminderEvent()
}