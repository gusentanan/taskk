package com.bagusmerta.taskk.data.preference.themes

import com.bagusmerta.taskk.model.preference.ThemePreference
import com.bagusmerta.taskk.presentation.screen.setting.ui.TaskkThemeItems

/**
 *  Mapper to proto-datastore
* */
fun TaskkTheme.toThemePref() =  when (this) {
    TaskkTheme.SYSTEM -> ThemePreference.SYSTEM
    TaskkTheme.LIGHT -> ThemePreference.LIGHT
    TaskkTheme.DARK -> ThemePreference.DARK
}
/**
 Mapper to model/enum classes
* */
fun ThemePreference.toTheme() = when (this) {
    ThemePreference.SYSTEM -> TaskkTheme.SYSTEM
    ThemePreference.LIGHT -> TaskkTheme.LIGHT
    ThemePreference.DARK -> TaskkTheme.DARK
}

/**
 * Extension to select themes with the latest selected data
 * @param theme enum class represent app themes
 * @return List of [TaskkThemeItems] which contain themes with flag if its applied or not
 */
fun List<TaskkThemeItems>.selectTheme(theme: TaskkTheme): List<TaskkThemeItems> {
    return map {
        it.copy(applied = it.type == theme)
    }
}
