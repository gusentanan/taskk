package com.bagusmerta.taskk.presentation.screen.taskk.data

import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow

interface IHomeEnvironment {
    val dateTimeProvider: DateTimeProvider

    fun getListTaskk(listId: String): Flow<TaskkList>

    fun getOverallCountTaskk(): Flow<TaskkOverallCount>
    suspend fun toggleTaskStatus(task: TaskkToDo)
    suspend fun deleteTask(task: TaskkToDo)

}

