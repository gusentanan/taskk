package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkRepeat
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface IDetailEnvironment {
    val idProvider: IdTaskkProvider
    val dateTimeProvider: DateTimeProvider

    fun getTaskkById(taskkId: String): Flow<TaskkToDo>
    suspend fun insertTaskkTitle(taskkId: String, title: String): Flow<TaskkToDo>
    suspend fun updateTaskkTitle(taskkId: String, title: String)
    suspend fun updateTaskkDueDate(taskkId: String, date: LocalDateTime)
    suspend fun resetTaskkDueDate(taskkId: String)
    suspend fun resetTaskkDueTime(taskkId: String, date: LocalDateTime)
    suspend fun updateTaskkPriority(taskkId: String, priority: TaskkPriority)
    suspend fun updateTaskkCategory(taskkId: String, category: TaskkCategory)
    suspend fun updateTaskkRepeat(taskkId: String, repeatable: TaskkRepeat)
    suspend fun updateTaskkNote(taskkId: String, note: String)
    suspend fun deleteTaskk(taskk: TaskkToDo)
    suspend fun toggleTaskStatus(taskk: TaskkToDo)
}