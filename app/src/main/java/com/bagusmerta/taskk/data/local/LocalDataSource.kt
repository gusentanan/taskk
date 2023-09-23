package com.bagusmerta.taskk.data.local

import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.Dispatcher
import com.bagusmerta.taskk.utils.mapper.toTaskkTodo
import com.bagusmerta.taskk.utils.mapper.toTaskkTodoDb
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Named

class LocalDataSource @Inject constructor(
    @Named(Dispatcher.DISPATCHER_IO) private val dispatcher: CoroutineDispatcher,
    private val taskkReadDao: TaskkReadDao,
    private val taskkWriteDao: TaskkWriteDao
){
    fun getListTaskk(): Flow<List<TaskkToDo>> {
        return taskkReadDao.getListTaskk()
            .filterNotNull()
            .map { it.toTaskkTodo() }
            .flowOn(dispatcher)
    }

    fun getTaskkById(id: String): Flow<TaskkToDo> {
        return taskkReadDao.getTaskkById(id)
            .map { it.toTaskkTodo() }
            .flowOn(dispatcher)
    }

    fun getOverallCountTaskk(): Flow<TaskkOverallCount> {
        return taskkReadDao.getOverallCountTaskk()
            .flowOn(dispatcher)
    }

    suspend fun insertTaskk(data: TaskkToDo){
        withContext(dispatcher){
            taskkWriteDao.insertTaskk(data.toTaskkTodoDb())
        }
    }

    suspend fun deleteTaskkById(id: String){
        withContext(dispatcher){
            taskkWriteDao.deleteTaskkById(id)
        }
    }

    suspend fun updateTaskkTitle(id: String, title: String) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkTitle(id, title)
        }
    }

    suspend fun updateTaskkCategory(id: String, category: TaskkCategory) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkCategory(id, category)
        }
    }

    suspend fun updateTaskkPriority(id: String, priority: TaskkPriority) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkPriority(id, priority)
        }
    }

    suspend fun updateTaskkNote(id: String, note: String){
        withContext(dispatcher){
            taskkWriteDao.updateTaskkNote(id, note)
        }
    }

    suspend fun updateTaskkDueDate(id: String, dueDate: LocalDate) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkDueDate(id, dueDate)
        }
    }
}