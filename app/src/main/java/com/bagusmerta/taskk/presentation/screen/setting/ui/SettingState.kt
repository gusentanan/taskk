package com.bagusmerta.taskk.presentation.screen.setting.ui

import com.bagusmerta.taskk.utils.themes.TaskkTheme

data class SettingState(
    val settingItems: List<TaskkThemeItems> = initial()
){

    companion object {
        fun initial() = listOf(
                TaskkThemeItems(
                    TaskkTheme.SYSTEM, "System Theme", false
                ),
                TaskkThemeItems(
                    TaskkTheme.LIGHT, "Light Theme", false
                ),
                TaskkThemeItems(
                    TaskkTheme.DARK, "Dark Theme", false
                )
            )
    }
}

data class TaskkThemeItems(
    val type: TaskkTheme,
    val title: String,
    val applied: Boolean
)
