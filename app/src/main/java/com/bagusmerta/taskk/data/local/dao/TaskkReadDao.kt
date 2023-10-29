package com.bagusmerta.taskk.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.domain.model.TaskkOverallCount
import com.bagusmerta.taskk.domain.model.TaskkStatus
import kotlinx.coroutines.flow.Flow

/**
 * Return the most recent value for TaskkToDo Resource, null if it doesn’t exist.
 */
@Dao
interface TaskkReadDao {

    @Query("SELECT * FROM TaskkTodoDb")
    fun getListTaskk(): Flow<List<TaskkTodoDb>>

    @Query("SELECT * FROM TaskkTodoDb WHERE taskk_status = :inCompleteStatus AND due_date = NOT NULL")
    fun getScheduledTaskk(inCompleteStatus: TaskkStatus = TaskkStatus.IN_PROGRESS): Flow<List<TaskkTodoDb>>

    @Query("SELECT * FROM TaskkTodoDb WHERE taskk_id = :taskkId")
    fun getTaskkById(taskkId: String): Flow<TaskkTodoDb>

    @Transaction
    @Query(
        """
        SELECT 
            (SELECT COUNT(*) FROM TaskkTodoDb WHERE taskk_status = :completedStatus) AS taskkCompleted,
            (SELECT COUNT(*) FROM TaskkTodoDb WHERE taskk_status = :inCompleteStatus) AS taskkInComplete
        """
    )
    fun getOverallCountTaskk(
        completedStatus: TaskkStatus = TaskkStatus.COMPLETE,
        inCompleteStatus: TaskkStatus = TaskkStatus.IN_PROGRESS
    ): Flow<TaskkOverallCount>

}