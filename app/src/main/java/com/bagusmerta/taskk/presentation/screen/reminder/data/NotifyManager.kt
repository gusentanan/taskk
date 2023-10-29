package com.bagusmerta.taskk.presentation.screen.reminder.data

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.net.toUri
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.data.local.db.toMillis
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.navigation.DetailFlow
import com.bagusmerta.taskk.presentation.screen.reminder.ui.TaskkBroadcastReceiver
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotifyManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

    init {
        initializeChannel()
    }

    fun show(taskk: TaskkToDo){
        val builder = buildNotification(taskk)
        val id = taskk.createdAt.toMillis().toInt()
        notificationManager?.notify(id, builder.build())
    }

    fun dismiss(taskk: TaskkToDo){
        val id = taskk.createdAt.toMillis().toInt()
        notificationManager?.cancel(id)
    }

    private fun buildNotification(taskk: TaskkToDo): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_notification)
            setContentTitle(taskk.name)
            setContentText(taskk.note)
            setStyle(NotificationCompat.BigTextStyle().bigText(taskk.note))
            setContentIntent(buildPendingIntent(taskk.id))
            setAutoCancel(true)
            setColorized(true)
            setShowWhen(false)
            addAction(getSnoozeEvent(taskk.id))
            addAction(getDoneEvent(taskk.id))
        }
    }

    private fun buildPendingIntent(taskkId: String): PendingIntent {
        val openTaskkIntent = Intent(Intent.ACTION_VIEW, DetailFlow.DetailScreen.deeplink(taskkId, "1").toUri())

        val flags = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        } else {
            PendingIntent.FLAG_UPDATE_CURRENT
        }

        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(openTaskkIntent)
            getPendingIntent(REQUEST_CODE_OPEN, flags)
        }
    }

    private fun getDoneEvent(taskId: String): NotificationCompat.Action {
        val actionTitle = context.getString(R.string.taskk_done)
        val intent = getPendingIntent(taskId, TaskkBroadcastReceiver.EVENT_NOTIFIY_DONE, REQUEST_CODE_EVENT_COMPLETE)
        return NotificationCompat.Action(REQUEST_CODE_NO_ICON, actionTitle, intent)
    }

    private fun getSnoozeEvent(taskId: String): NotificationCompat.Action {
        val actionTitle = context.getString(R.string.taskk_snooze)
        val intent = getPendingIntent(taskId, TaskkBroadcastReceiver.EVENT_NOTIFY_SNOOZE, REQUEST_CODE_EVENT_SNOOZE)
        return NotificationCompat.Action(REQUEST_CODE_NO_ICON, actionTitle, intent)
    }

    private fun getPendingIntent(
        taskId: String,
        intentAction: String,
        requestCode: Int
    ): PendingIntent {
        val receiverIntent = Intent(context, TaskkBroadcastReceiver::class.java).apply {
            action = intentAction
            putExtra(TaskkBroadcastReceiver.TASKK_ID, taskId)
        }

        return PendingIntent
            .getBroadcast(
                context,
                requestCode,
                receiverIntent,
                PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
    }


    private fun initializeChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = context.getString(R.string.taskk_channel)
            val description = context.getString(R.string.taskk_channel_desc)
            val importanceLevel = NotificationManager.IMPORTANCE_HIGH

            NotificationChannel(CHANNEL_ID, name, importanceLevel).apply {
                this.description = description
                enableLights(true)
                lightColor = ResourcesCompat.getColor(context.resources, R.color.light_primary, null)
                enableVibration(true)
                notificationManager?.createNotificationChannel(this)
            }

        }
    }


    companion object {
        private const val REQUEST_CODE_NO_ICON = 0
        private const val REQUEST_CODE_OPEN = 1
        private const val REQUEST_CODE_EVENT_COMPLETE = 2
        private const val REQUEST_CODE_EVENT_SNOOZE = 3
        private const val  CHANNEL_ID = "taskk_notification_channel"
    }
}