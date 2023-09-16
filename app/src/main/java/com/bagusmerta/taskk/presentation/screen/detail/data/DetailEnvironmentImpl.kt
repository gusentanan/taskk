package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailEnvironmentImpl @Inject constructor(
    override val idProvider: IdTaskkProvider,
    override val dateTimeProvider: DateTimeProvider
): IDetailEnvironment {
    override fun getTaskkById(taskkId: String): Flow<TaskkToDo> {
        TODO("Not yet implemented")
    }

    override fun insertTaskkTitle(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskkDueDate(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskkPriority(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskkCategory(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskkNote(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskkStatus(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteTaskk(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun toggleTaskStatus(taskkId: String) {
        TODO("Not yet implemented")
    }
}