package com.bagusmerta.taskk.presentation.screen.reminder.data

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.AlarmManagerCompat
import com.bagusmerta.taskk.data.local.db.toMillis
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.presentation.screen.reminder.ui.TaskkBroadcastReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlarmManager @Inject constructor(
    @ApplicationContext private val context: Context
){

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private val flags = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    } else {
        PendingIntent.FLAG_UPDATE_CURRENT
    }

    fun scheduleTaskkAlarm(taskk: TaskkToDo, time: LocalDateTime){
        val receiverIntent = Intent(context, TaskkBroadcastReceiver::class.java).apply {
            action = TaskkBroadcastReceiver.EVENT_SHOW_ALARM
            putExtra(TaskkBroadcastReceiver.TASKK_ID, taskk.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskk.createdAt.toMillis().toInt(),
            receiverIntent,
            flags
        )

        setAlarm(time.toMillis(), pendingIntent)
    }

    fun cancelTaskkAlarm(taskk: TaskkToDo){
        val receiverIntent = Intent(context, TaskkBroadcastReceiver::class.java).apply {
            action = TaskkBroadcastReceiver.EVENT_SHOW_ALARM
            putExtra(TaskkBroadcastReceiver.TASKK_ID, taskk.id)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            taskk.createdAt.toMillis().toInt(),
            receiverIntent,
            flags
        )

        cancelAlarm(pendingIntent)
    }

    private fun setAlarm(
        triggerAtMillis: Long,
        operation: PendingIntent?
    ) {
        if(operation == null){
            return
        }

        alarmManager?.let {
            AlarmManagerCompat.setAndAllowWhileIdle(it, AlarmManager.RTC_WAKEUP, triggerAtMillis, operation)
        }
    }

    private fun cancelAlarm(
        operation: PendingIntent?
    ){
        if(operation == null){
            return
        }
        alarmManager?.cancel(operation)

    }

}