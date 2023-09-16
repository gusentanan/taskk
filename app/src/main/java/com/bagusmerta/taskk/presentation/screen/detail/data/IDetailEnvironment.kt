package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow

/*
 * Note.
 * Local transactions will proceed only if the Task Title is not null,
 * and each transaction will target only one specific change to each
 * data class property.
 */
interface IDetailEnvironment {
    val idProvider: IdTaskkProvider
    val dateTimeProvider: DateTimeProvider

    fun getTaskkById(taskkId: String): Flow<TaskkToDo>
    fun insertTaskkTitle(taskkId: String)
    fun updateTaskkDueDate(taskkId: String)
    fun updateTaskkPriority(taskkId: String)
    fun updateTaskkCategory(taskkId: String)
    fun updateTaskkNote(taskkId: String)
    fun updateTaskkStatus(taskkId: String)
    fun deleteTaskk(taskkId: String)
    fun toggleTaskStatus(taskkId: String)
}