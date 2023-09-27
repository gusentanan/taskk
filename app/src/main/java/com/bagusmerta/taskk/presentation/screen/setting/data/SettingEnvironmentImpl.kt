package com.bagusmerta.taskk.presentation.screen.setting.data

import com.bagusmerta.taskk.data.preference.PreferenceManager
import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingEnvironmentImpl @Inject constructor(
    private val preference: PreferenceManager
): ISettingEnvironment {

    override fun getTheme(): Flow<TaskkTheme> {
        return preference.getTheme()
    }

    override suspend fun setTheme(theme: TaskkTheme) {
        preference.setTheme(theme)
    }

}