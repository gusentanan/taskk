package com.bagusmerta.taskk.presentation.screen.host.di

import com.bagusmerta.taskk.presentation.screen.host.data.HostEnvironment
import com.bagusmerta.taskk.presentation.screen.host.data.IHostEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HostModule {

    @Binds
    abstract fun provideHost(
        hostEnv: HostEnvironment
    ): IHostEnvironment
}