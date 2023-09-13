package com.bagusmerta.taskk.di

import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonModule {

    @Singleton
    @Provides
    fun provideIdProvider(): IdTaskkProvider {
        return IdTaskkProviderImpl()
    }

    @Singleton
    @Provides
    fun provideDateTimeProvider(): DateTimeProvider {
        return DateTimeProviderImpl()
    }

}