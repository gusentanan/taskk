package com.bagusmerta.taskk.presentation.screen.setting.ui

sealed class SettingEvent {
    data class SelectedTheme(val selectedTheme: TaskkThemeItems): SettingEvent()
}