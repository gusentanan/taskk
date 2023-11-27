package com.bagusmerta.taskk.presentation.screen.host.data

import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import kotlinx.coroutines.flow.Flow

interface IHostEnvironment {
    fun getTheme(): Flow<TaskkTheme>
}