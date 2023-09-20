package com.bagusmerta.taskk.presentation.screen.taskk.data

import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow

interface IHomeEnvironment {
    val idProvider: IdTaskkProvider
    val dateTimeProvider: DateTimeProvider

    fun getListTaskk(listId: String): Flow<TaskkList>
    suspend fun createTask(taskName: String, listId: String): Flow<TaskkToDo>
    suspend fun toggleTaskStatus(toDoTask: TaskkToDo)
    suspend fun deleteTask(task: TaskkToDo)
    fun trackSaveListButtonClicked()
}

