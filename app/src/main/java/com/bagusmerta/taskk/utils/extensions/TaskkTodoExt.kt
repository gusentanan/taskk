package com.bagusmerta.taskk.utils.extensions

import android.content.res.Resources
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkItem
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkListState
import java.time.LocalDateTime


fun TaskkList.toTaskkListState(): TaskkListState{
    val tasks = tasks.map {
        when(it.status){
            TaskkStatus.COMPLETE -> TaskkItem.Complete(it)
            TaskkStatus.IN_PROGRESS -> TaskkItem.InProgress(it)
        }
    }.sortedBy {
        it is TaskkItem.Complete
    }.toMutableList()

    val firstCompletedIndex = tasks.indexOfFirst { it is TaskkItem.Complete }
    if(firstCompletedIndex != -1){
        tasks.add(firstCompletedIndex, TaskkItem.CompleteHeader())
    }

    return TaskkListState(
        id = id,
        taskkItem = tasks
    )
}

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
