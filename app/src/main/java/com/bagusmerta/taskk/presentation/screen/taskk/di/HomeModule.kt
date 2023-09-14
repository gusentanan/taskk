package com.bagusmerta.taskk.presentation.screen.taskk.di

import com.bagusmerta.taskk.presentation.screen.taskk.data.HomeEnvironmentImpl
import com.bagusmerta.taskk.presentation.screen.taskk.data.IHomeEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {
    @Binds
    abstract fun provideHomeEnvironment(
        homeEnv: HomeEnvironmentImpl
    ): IHomeEnvironment
}