package com.bagusmerta.taskk.presentation.screen.setting.di

import com.bagusmerta.taskk.presentation.screen.setting.data.ISettingEnvironment
import com.bagusmerta.taskk.presentation.screen.setting.data.SettingEnvironmentImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class SettingModule {

    @Binds
    abstract fun provideSettingEnvironment(
        settingEnv: SettingEnvironmentImpl
    ): ISettingEnvironment
}