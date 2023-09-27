package com.bagusmerta.taskk.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import kotlinx.coroutines.DelicateCoroutinesApi

@Database(
    entities = [TaskkTodoDb::class], version = 1,
)
@TypeConverters(DateConverter::class)
abstract class TaskkDatabase: RoomDatabase(){
    abstract fun taskkWriteDao(): TaskkWriteDao
    abstract fun taskkReadDao(): TaskkReadDao

    @DelicateCoroutinesApi
    companion object {
        private const val TASKK_DB_NAME = "taskk-db"

        @Volatile
        private var INSTANCE: TaskkDatabase? = null

        fun getInstance(context: Context): TaskkDatabase {
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: buildDb(context).also { INSTANCE = it }
            }
        }

        private fun buildDb(context: Context): TaskkDatabase {
            val db = Room.databaseBuilder(
                context,
                TaskkDatabase::class.java,
                TASKK_DB_NAME
            )

            return db.build()
        }

    }
}
