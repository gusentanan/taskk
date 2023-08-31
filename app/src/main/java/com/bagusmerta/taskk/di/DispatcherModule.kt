package com.bagusmerta.taskk.di

import com.bagusmerta.taskk.utils.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    @Named(Dispatcher.DISPATCHER_IO)
    fun provideDispatcherIo(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    @Provides
    @Named(Dispatcher.DISPATCHER_MAIN)
    fun provideDispatcherMain(): CoroutineDispatcher {
        return Dispatchers.Main
    }
}