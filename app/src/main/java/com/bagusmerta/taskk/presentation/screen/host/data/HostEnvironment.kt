package com.bagusmerta.taskk.presentation.screen.host.data


import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import com.bagusmerta.taskk.data.preference.themes.ThemeProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface IHostEnvironment {
    fun getTheme(): Flow<TaskkTheme>
}

class HostEnvironment @Inject constructor(
    private val themeProvider: ThemeProvider
) : IHostEnvironment {

    override fun getTheme(): Flow<TaskkTheme> {
        return themeProvider.getTheme()
    }
}