package com.bagusmerta.taskk.presentation.screen.setting.data

import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import kotlinx.coroutines.flow.Flow

interface ISettingEnvironment {
    fun getTheme(): Flow<TaskkTheme>
    suspend fun setTheme(theme: TaskkTheme)
}