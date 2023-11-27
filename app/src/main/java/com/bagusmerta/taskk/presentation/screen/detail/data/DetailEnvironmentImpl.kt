package com.bagusmerta.taskk.presentation.screen.detail.data

import com.bagusmerta.taskk.data.local.LocalDataSource
import com.bagusmerta.taskk.model.TaskkCategory
import com.bagusmerta.taskk.model.TaskkPriority
import com.bagusmerta.taskk.model.TaskkRepeat
import com.bagusmerta.taskk.model.TaskkStatus
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.IdTaskkProvider
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import javax.inject.Inject

class DetailEnvironmentImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    override val idProvider: IdTaskkProvider,
    override val dateTimeProvider: DateTimeProvider
): IDetailEnvironment {
    override fun getTaskkById(taskkId: String): Flow<TaskkToDo> {
        return localDataSource.getTaskkById(taskkId)
    }

    // First time in; only insert id, title, timestamp
    override suspend fun insertTaskkTitle(taskkId: String,title: String): Flow<TaskkToDo> {
        localDataSource.insertTaskk(
            TaskkToDo(
                id = taskkId,
                name = title,
                createdAt = dateTimeProvider.getNowDate(),
                updatedAt = dateTimeProvider.getNowDate()
            )
        )

        // Use this for relaunch as workaround
        return getTaskkById(taskkId)
    }

    override suspend fun updateTaskkTitle(taskkId: String, title: String) {
        localDataSource.updateTaskkTitle(taskkId, title, dateTimeProvider.getNowDate())
    }

    override suspend fun updateTaskkDueDate(taskkId: String, date: LocalDateTime) {
        localDataSource.updateTaskkDueDate(taskkId, date, dateTimeProvider.getNowDate(), true)
    }

    override suspend fun resetTaskkDueDate(taskkId: String) {
        localDataSource.resetTaskkDueDate(taskkId, dateTimeProvider.getNowDate())
    }

    override suspend fun resetTaskkDueTime(taskkId: String, date: LocalDateTime) {
        localDataSource.updateTaskkDueDate(taskkId, date, dateTimeProvider.getNowDate(), false)
    }

    override suspend fun updateTaskkPriority(taskkId: String, priority: TaskkPriority) {
        localDataSource.updateTaskkPriority(taskkId, priority, dateTimeProvider.getNowDate())
    }

    override suspend fun updateTaskkCategory(taskkId: String, category: TaskkCategory) {
        localDataSource.updateTaskkCategory(taskkId, category, dateTimeProvider.getNowDate())
    }

    override suspend fun updateTaskkRepeat(taskkId: String, repeatable: TaskkRepeat) {
        localDataSource.updateTaskkRepeat(taskkId, repeatable, dateTimeProvider.getNowDate())
    }

    override suspend fun updateTaskkNote(taskkId: String, note: String) {
        localDataSource.updateTaskkNote(taskkId, note, dateTimeProvider.getNowDate())
    }

    override suspend fun deleteTaskk(taskk: TaskkToDo) {
        localDataSource.deleteTaskkById(taskk.id)
    }

    override suspend fun toggleTaskStatus(taskk: TaskkToDo) {
        val taskkStatus = when(taskk.status){
            TaskkStatus.IN_PROGRESS -> { TaskkStatus.COMPLETE }
            TaskkStatus.COMPLETE -> { TaskkStatus.IN_PROGRESS }
        }
        localDataSource.updateTaskkStatus(taskk.id, taskkStatus, dateTimeProvider.getNowDate())
    }
}