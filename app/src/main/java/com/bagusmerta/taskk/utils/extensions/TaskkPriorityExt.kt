package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.presentation.screen.detail.ui.PriorityItems

fun List<PriorityItems>.select(item: TaskkPriority): List<PriorityItems> {
    return map {
        it.copy(applied = it.priority == item)
    }
}

fun TaskkPriority.displayable(): String {
    return when (this) {
        TaskkPriority.EASY -> "Easy"
        TaskkPriority.MID -> "Medium"
        TaskkPriority.HARD -> "Hard"
    }
}
