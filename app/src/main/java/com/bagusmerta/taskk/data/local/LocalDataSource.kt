package com.bagusmerta.taskk.data.local

import com.bagusmerta.taskk.data.local.dao.TaskkReadDao
import com.bagusmerta.taskk.data.local.dao.TaskkWriteDao
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkStatus
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
import java.time.LocalDateTime
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

    fun getScheduledTaskk(): Flow<List<TaskkToDo>> {
        return taskkReadDao.getScheduledTaskk()
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

    suspend fun updateTaskkTitle(id: String, title: String, updateAt: LocalDateTime) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkTitle(id, title, updateAt)
        }
    }

    suspend fun updateTaskkCategory(id: String, category: TaskkCategory, updateAt: LocalDateTime) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkCategory(id, category, updateAt)
        }
    }

    suspend fun updateTaskkPriority(id: String, priority: TaskkPriority, updateAt: LocalDateTime) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkPriority(id, priority, updateAt)
        }
    }

    suspend fun updateTaskkNote(id: String, note: String,  updateAt: LocalDateTime){
        withContext(dispatcher){
            taskkWriteDao.updateTaskkNote(id, note, updateAt)
        }
    }

    suspend fun updateTaskkDueDate(id: String, dueDate: LocalDateTime,  updateAt: LocalDateTime, isDueDateSet: Boolean) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkDueDate(id, dueDate, updateAt, isDueDateSet)
        }
    }

    suspend fun resetTaskkDueDate(id: String,  updateAt: LocalDateTime) {
        withContext(dispatcher){
            taskkWriteDao.updateTaskkDueDate(id, null, updateAt, false)
        }
    }

    suspend fun updateTaskkStatus(id: String, taskkStatus: TaskkStatus,  updateAt: LocalDateTime){
        withContext(dispatcher){
            taskkWriteDao.updateTaskkStatus(taskkStatus, id, updateAt)
        }
    }
}