package com.bagusmerta.taskk.presentation.screen.taskk.data

import androidx.constraintlayout.utils.widget.MockView
import com.bagusmerta.taskk.data.model.TaskkList
import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.utils.getMockListTask
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import java.time.LocalDateTime
import javax.inject.Inject

class HomeEnvironmentImpl @Inject constructor(
    override val idProvider: IdTaskkProvider,
    override val dateTimeProvider: DateTimeProvider
): IHomeEnvironment {

    override fun getListTaskk(listId: String): Flow<TaskkList> {
        return getMockListTask()
    }

    override suspend fun createTask(taskName: String, listId: String): Flow<TaskkToDo> {
        TODO("Not yet implemented")
    }

    override suspend fun toggleTaskStatus(toDoTask: TaskkToDo) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTask(task: TaskkToDo) {
        TODO("Not yet implemented")
    }

    override fun trackSaveListButtonClicked() {
        TODO("Not yet implemented")
    }
}