package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.presentation.screen.detail.ui.PriorityItems

fun List<PriorityItems>.select(item: TaskkPriority): List<PriorityItems> {
    return map {
        it.copy(applied = it.priority == item)
    }
}

fun TaskkPriority.displayable(): Int {
    return when (this) {
        TaskkPriority.EASY -> R.string.taskk_priority_displayable_easy
        TaskkPriority.MID -> R.string.taskk_priority_displayable_mid
        TaskkPriority.HARD -> R.string.taskk_priority_displayable_hard
    }
}
