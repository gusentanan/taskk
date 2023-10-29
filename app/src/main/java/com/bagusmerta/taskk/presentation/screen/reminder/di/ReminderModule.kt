package com.bagusmerta.taskk.presentation.screen.reminder.di

import com.bagusmerta.taskk.presentation.screen.reminder.data.IReminderEnvironment
import com.bagusmerta.taskk.presentation.screen.reminder.data.ReminderEnvironmentImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ReminderModule {
    @Binds
    abstract fun provideReminderEnvironment(
        reminderEnv: ReminderEnvironmentImpl
    ): IReminderEnvironment
}