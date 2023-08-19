package com.bagusmerta.taskk.utils.themes

import com.bagusmerta.taskk.model.preference.ThemePreference

/*
* Mapper to proto-datastore
* */
fun TaskkTheme.toThemePref() =  when (this) {
    TaskkTheme.SYSTEM -> ThemePreference.SYSTEM
    TaskkTheme.LIGHT -> ThemePreference.LIGHT
    TaskkTheme.DARK -> ThemePreference.DARK
}

/*
* Mapper to model/enum classes
* */
fun ThemePreference.toTheme() = when (this) {
    ThemePreference.SYSTEM -> TaskkTheme.SYSTEM
    ThemePreference.LIGHT -> TaskkTheme.LIGHT
    ThemePreference.DARK -> TaskkTheme.DARK
    ThemePreference.UNRECOGNIZED -> TODO()
}