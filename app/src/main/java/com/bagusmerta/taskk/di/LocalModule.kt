package com.bagusmerta.taskk.di

import android.content.Context
import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.data.local.db.TaskkDatabase
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Singleton


@DelicateCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    fun provideTaskkWriteDao(@ApplicationContext context: Context): TaskkWriteDao {
        return TaskkDatabase.getInstance(context).taskkWriteDao()
    }

    @Singleton
    fun provideTaskkReadDao(@ApplicationContext context: Context): TaskkReadDao {
        return TaskkDatabase.getInstance(context).taskkReadDao()
    }
}