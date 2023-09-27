package com.bagusmerta.taskk.presentation.screen.setting.ui

import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.utils.themes.TaskkTheme

data class SettingState(
    val settingItems: List<TaskkThemeItems> = initial()
){

    companion object {
        fun initial() = listOf(
                TaskkThemeItems(
                    TaskkTheme.SYSTEM, R.string.system_theme_text, false
                ),
                TaskkThemeItems(
                    TaskkTheme.LIGHT, R.string.light_theme_text, false
                ),
                TaskkThemeItems(
                    TaskkTheme.DARK, R.string.dark_theme_text, false
                )
            )
    }
}

data class TaskkThemeItems(
    val type: TaskkTheme,
    val title: Int,
    val applied: Boolean
)
