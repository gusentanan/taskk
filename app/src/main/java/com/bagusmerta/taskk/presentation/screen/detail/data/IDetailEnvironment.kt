package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow

interface IDetailEnvironment {
    val idProvider: IdTaskkProvider
    val dateTimeProvider: DateTimeProvider

    fun getTaskk(taskkId: String): Flow<TaskkToDo>
    fun addTaskk(taskkId: String)
    fun updateTaskk(taskkId: String)
    fun deleteTaskk(taskkId: String)
}