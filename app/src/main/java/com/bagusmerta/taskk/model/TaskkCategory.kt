package com.bagusmerta.taskk.model

import com.bagusmerta.taskk.R

enum class TaskkCategory(val str: Int) {
    WORK(R.string.taskk_category_work),
    HOUSEHOLD(R.string.taskk_category_house),
    STUDY(R.string.taskk_category_study),
    SELF_HELP(R.string.taskk_category_selfhelp),
    GYM(R.string.taskk_category_gym)
}