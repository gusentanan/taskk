package com.bagusmerta.taskk.extension

import com.bagusmerta.taskk.data.preference.themes.TaskkTheme
import com.bagusmerta.taskk.data.preference.themes.toTheme
import com.bagusmerta.taskk.data.preference.themes.toThemePref
import com.bagusmerta.taskk.model.preference.ThemePreference
import org.junit.Assert
import org.junit.Test


class ThemeExtTest {
    @Test
    fun mapToPreference(){
        Assert.assertEquals(ThemePreference.DARK, TaskkTheme.DARK.toThemePref())
        Assert.assertEquals(ThemePreference.SYSTEM, TaskkTheme.SYSTEM.toThemePref())
        Assert.assertEquals(ThemePreference.LIGHT, TaskkTheme.LIGHT.toThemePref())
    }

    @Test
    fun mapToTheme(){
        Assert.assertEquals(TaskkTheme.DARK, ThemePreference.DARK.toTheme())
        Assert.assertEquals(TaskkTheme.SYSTEM, ThemePreference.SYSTEM.toTheme())
        Assert.assertEquals(TaskkTheme.LIGHT, ThemePreference.LIGHT.toTheme())
    }
}