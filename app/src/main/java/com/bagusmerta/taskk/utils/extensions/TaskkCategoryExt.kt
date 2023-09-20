package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.presentation.screen.detail.ui.CategoryItems
import com.bagusmerta.taskk.presentation.screen.detail.ui.PriorityItems

fun List<CategoryItems>.select(item: TaskkCategory): List<CategoryItems> {
    return map {
        it.copy(applied = it.category == item)
    }
}

fun TaskkCategory.displayable(): String {
    return when (this) {
        TaskkCategory.STUDY -> TaskkCategory.STUDY.str
        TaskkCategory.WORK -> TaskkCategory.WORK.str
        TaskkCategory.GYM -> TaskkCategory.GYM.str
        TaskkCategory.HOUSEHOLD -> TaskkCategory.HOUSEHOLD.str
        TaskkCategory.SELF_HELP -> TaskkCategory.SELF_HELP.str
    }
}