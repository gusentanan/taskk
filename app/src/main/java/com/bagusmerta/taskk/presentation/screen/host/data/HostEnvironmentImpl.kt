package com.bagusmerta.taskk.presentation.screen.host.data


import com.bagusmerta.taskk.data.preference.PreferenceManager
import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HostEnvironmentImpl @Inject constructor(
    private val preferenceManager: PreferenceManager
) : IHostEnvironment {

    override fun getTheme(): Flow<TaskkTheme> {
        return preferenceManager.getTheme()
    }
}