package com.bagusmerta.taskk.utils.extensions

import android.content.res.Resources
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.model.TaskkList
import com.bagusmerta.taskk.model.TaskkRepeat
import com.bagusmerta.taskk.model.TaskkStatus
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkItem
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkListState
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

private val DEFAULT_TASK_LOCAL_TIME: LocalTime = LocalTime.of(23, 59)

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

fun TaskkToDo.isDueDateSet(): Boolean = this.isDueDateTimeSet
fun TaskkToDo.isExpired(currentDate: LocalDateTime = DateTimeProviderImpl().getNowDate()): Boolean {
    return dueDate?.isBefore(currentDate) ?: false
}
fun TaskkToDo.updatedDateToLocalDateTime(newLocalDate: LocalDate): LocalDateTime {
    val localTime = dueDate?.toLocalTime() ?: DEFAULT_TASK_LOCAL_TIME
    return LocalDateTime.of(newLocalDate, localTime)
}
fun TaskkToDo.updateTimeOnLocalDate(defaultDate: LocalDate, newLocalTime: LocalTime): LocalDateTime {
    val localDate = dueDate?.toLocalDate() ?: defaultDate
    return LocalDateTime.of(localDate, newLocalTime)
}

fun TaskkToDo.displayTime(): String? {
    return if (isDueDateTimeSet) dueDate?.toLocalTime().toString() else  null
}

fun TaskkToDo.getNextScheduledDate(currentDate: LocalDateTime): LocalDateTime {
    require(dueDate != null)
    var usedDate = if(isExpired(currentDate)){
        LocalDateTime.of(currentDate.toLocalDate(), dueDate.toLocalTime())
    } else {
        dueDate
    }

    usedDate =  when(taskkRepeat){
            TaskkRepeat.DAILY -> {
                dueDate.plusDays(1)
            }
            TaskkRepeat.WEEKLY -> {
                dueDate.plusWeeks(1)
            }
            TaskkRepeat.MONTHLY -> {
                dueDate.plusMonths(1)
            }
            TaskkRepeat.YEARLY -> {
                dueDate.plusYears(1)
            }
            TaskkRepeat.NEVER -> {
                usedDate
            }
    }

    return usedDate
}

fun TaskkToDo.getCurrentScheduledDate(currentDate: LocalDateTime): LocalDateTime {
    require(dueDate != null)

    return if(taskkRepeat != TaskkRepeat.NEVER){
        if(isExpired(currentDate)){
           currentDate.plusMinutes(1)
        } else {
            dueDate
        }
    } else dueDate

}

suspend fun TaskkToDo.toggleStatusHandler(
    currentDate: LocalDateTime,
    onUpdateStatus: suspend (LocalDateTime?, TaskkStatus) -> Unit,
    onUpdateDueDate: suspend (LocalDateTime) -> Unit,
) {
    if(taskkRepeat == TaskkRepeat.NEVER){
        val newStatus = status.toggle()
        val completedAt = when (newStatus) {
            TaskkStatus.IN_PROGRESS -> null
            TaskkStatus.COMPLETE -> currentDate
        }
        onUpdateStatus(completedAt, newStatus)
    } else {
        val nextDueDate = getNextScheduledDate(currentDate)
        onUpdateDueDate(nextDueDate)
    }
}

fun TaskkStatus.toggle(): TaskkStatus {
    return when(this){
        TaskkStatus.COMPLETE -> TaskkStatus.IN_PROGRESS
        TaskkStatus.IN_PROGRESS -> TaskkStatus.COMPLETE
    }
}
