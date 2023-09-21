package com.bagusmerta.taskk.presentation.screen.setting.data

import com.bagusmerta.taskk.model.preference.ThemePreference
import com.bagusmerta.taskk.utils.PreferenceManager
import com.bagusmerta.taskk.utils.themes.TaskkTheme
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