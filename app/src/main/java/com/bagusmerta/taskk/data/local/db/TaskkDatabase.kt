package com.bagusmerta.taskk.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import kotlinx.coroutines.DelicateCoroutinesApi

@Database(
    entities = [TaskkTodoDb::class], version = 1,
)
abstract class TaskkDatabase: RoomDatabase(){
    
}
