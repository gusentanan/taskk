package com.bagusmerta.taskk.presentation.designsystem.component

import android.content.res.Resources
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.utils.formatDateTime
import com.bagusmerta.taskk.utils.isSameDay
import com.bagusmerta.taskk.utils.isTomorrow
import com.bagusmerta.taskk.utils.isYesterday
import java.time.LocalDateTime

fun TaskkToDo.dueDateDisplayable(resources: Resources, currentDate: LocalDateTime = LocalDateTime.now()): String? {
    return if (dueDate != null) {
        when {
            dueDate.isSameDay(currentDate) -> resources.getString(R.string.taskk_due_date_today)
            dueDate.isTomorrow(currentDate) -> resources.getString(R.string.taskk_due_date_tomorrow)
            dueDate.isYesterday(currentDate) -> resources.getString(R.string.taskk_due_date_yesterday)
            else -> resources.getString(R.string.taskk_due_date, dueDate.formatDateTime())
        }
    } else {
        null
    }
}
