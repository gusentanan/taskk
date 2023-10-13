package com.bagusmerta.taskk.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.bagusmerta.taskk.data.preference.themes.ThemeSerializer
import com.bagusmerta.taskk.model.preference.ThemePreference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val THEME_NAME = "theme-preference.pb"

private val Context.themeDataStore: DataStore<ThemePreference> by dataStore(
    fileName = THEME_NAME,
    serializer = ThemeSerializer
)


@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<ThemePreference> {
        return context.themeDataStore
    }
}