package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailEnvironmentImpl @Inject constructor(
    override val idProvider: IdTaskkProvider,
    override val dateTimeProvider: DateTimeProvider
): IDetailEnvironment {

    override fun getTaskk(taskkId: String): Flow<TaskkToDo> {
        TODO("Not yet implemented")
    }

    override fun addTaskk(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun updateTaskk(taskkId: String) {
        TODO("Not yet implemented")
    }

    override fun deleteTaskk(taskkId: String) {
        TODO("Not yet implemented")
    }
}