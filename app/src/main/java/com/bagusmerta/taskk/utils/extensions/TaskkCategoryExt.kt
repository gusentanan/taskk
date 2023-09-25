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
        TaskkCategory.STUDY -> TaskCategoryDisplayableOnDetail.STUDY.title
        TaskkCategory.WORK -> TaskCategoryDisplayableOnDetail.WORK.title
        TaskkCategory.GYM -> TaskCategoryDisplayableOnDetail.GYM.title
        TaskkCategory.HOUSEHOLD -> TaskCategoryDisplayableOnDetail.HOUSEHOLD.title
        TaskkCategory.SELF_HELP -> TaskCategoryDisplayableOnDetail.SELF_HELP.title
    }
}

enum class TaskCategoryDisplayableOnDetail(val title: String){
    STUDY("Study"),
    WORK("Work Related"),
    GYM("Gym / Workout"),
    HOUSEHOLD("House Related"),
    SELF_HELP("Self Improvement")
}