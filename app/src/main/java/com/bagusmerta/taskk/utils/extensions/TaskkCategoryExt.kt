package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.model.TaskkCategory
import com.bagusmerta.taskk.presentation.screen.detail.ui.CategoryItems

fun List<CategoryItems>.select(item: TaskkCategory): List<CategoryItems> {
    return map {
        it.copy(applied = it.category == item)
    }
}

fun TaskkCategory.displayable(): Int {
    return when (this) {
        TaskkCategory.STUDY -> R.string.taskk_category_displayable_study
        TaskkCategory.WORK -> R.string.taskk_category_displayable_work
        TaskkCategory.GYM -> R.string.taskk_category_displayable_gym
        TaskkCategory.HOUSEHOLD -> R.string.taskk_category_displayable_house
        TaskkCategory.SELF_HELP -> R.string.taskk_category_displayable_selfhelp
    }
}
