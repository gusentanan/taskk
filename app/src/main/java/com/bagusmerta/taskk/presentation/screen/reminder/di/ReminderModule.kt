package com.bagusmerta.taskk.presentation.screen.reminder.di

import com.bagusmerta.taskk.presentation.screen.reminder.data.IReminderEnvironment
import com.bagusmerta.taskk.presentation.screen.reminder.data.ReminderEnvironmentImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReminderModule {
    @Binds
    abstract fun provideReminderEnvironment(
        reminderEnv: ReminderEnvironmentImpl
    ): IReminderEnvironment
}