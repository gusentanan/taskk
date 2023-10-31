package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkRepeat
import com.bagusmerta.taskk.presentation.screen.detail.ui.PriorityItems
import com.bagusmerta.taskk.presentation.screen.detail.ui.RepeatableItems

fun List<RepeatableItems>.select(item: TaskkRepeat): List<RepeatableItems> {
    return map {
        it.copy(applied = it.repeat == item)
    }
}

fun TaskkRepeat.displayable(): Int {
    return when (this) {
        TaskkRepeat.NEVER -> R.string.taskk_repeat_displayable_never
        TaskkRepeat.DAILY -> R.string.taskk_repeat_displayable_daily
        TaskkRepeat.WEEKLY -> R.string.taskk_repeat_displayable_weekly
        TaskkRepeat.MONTHLY-> R.string.taskk_repeat_displayable_monthly
        TaskkRepeat.YEARLY-> R.string.taskk_repeat_displayable_yearly

    }
}