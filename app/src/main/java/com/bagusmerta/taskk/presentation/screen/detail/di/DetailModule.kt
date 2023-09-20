package com.bagusmerta.taskk.presentation.screen.detail.di

import com.bagusmerta.taskk.presentation.screen.detail.data.DetailEnvironmentImpl
import com.bagusmerta.taskk.presentation.screen.detail.data.IDetailEnvironment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DetailModule {
    @Binds
    abstract fun provideDetailEnvironment(
        detailEnv: DetailEnvironmentImpl
    ): IDetailEnvironment
}