package com.bagusmerta.taskk.presentation.screen.taskk.data

import com.bagusmerta.taskk.data.local.LocalDataSource
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HomeEnvironmentImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    override val dateTimeProvider: DateTimeProvider
): IHomeEnvironment {

    override fun getListTaskk(listId: String): Flow<TaskkList> {
        return localDataSource.getListTaskk().map {
            TaskkList(
                id = "1", // Set as 1 since for now we only expect one list of a task
                tasks = it
            )
        }
    }

    override fun getOverallCountTaskk(): Flow<TaskkOverallCount> {
        return localDataSource.getOverallCountTaskk()
    }

    override suspend fun toggleTaskStatus(task: TaskkToDo) {
        val taskkStatus = when(task.status){
            TaskkStatus.IN_PROGRESS -> { TaskkStatus.COMPLETE }
            TaskkStatus.COMPLETE -> { TaskkStatus.IN_PROGRESS }
        }
        localDataSource.updateTaskkStatus(task.id, taskkStatus, dateTimeProvider.getNowDate())
    }


    override suspend fun deleteTask(task: TaskkToDo) {
        localDataSource.deleteTaskkById(task.id)
    }

}