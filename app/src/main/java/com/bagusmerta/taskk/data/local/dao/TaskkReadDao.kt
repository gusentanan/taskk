package com.bagusmerta.taskk.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.model.TaskkOverallCount
import com.bagusmerta.taskk.model.TaskkStatus
import kotlinx.coroutines.flow.Flow

/**
 * Return the most recent value for TaskkToDo Resource, null if it doesn’t exist.
 */
@Dao
interface TaskkReadDao {

    /**
     * Get a list of task from Local DB
     */
    @Query("SELECT * FROM TaskkTodoDb ORDER BY due_date ASC")
    fun getListTaskk(): Flow<List<TaskkTodoDb>>

    /**
     * Get the scheduled task by its status (default as IN_PROGRESS)
     * @param inCompleteStatus Task completion status
     */
    @Query("SELECT * FROM TaskkTodoDb WHERE taskk_status = :inCompleteStatus AND due_date IS NOT NULL")
    fun getScheduledTaskk(inCompleteStatus: TaskkStatus = TaskkStatus.IN_PROGRESS): Flow<List<TaskkTodoDb>>

    /**
     * Get task by its ID
     * @param taskkId  ID of a task
     */
    @Query("SELECT * FROM TaskkTodoDb WHERE taskk_id = :taskkId")
    fun getTaskkById(taskkId: String): Flow<TaskkTodoDb>


    /**
     * Handles overall-counts of task by its status
     * @param completedStatus  task completed status
     * @param inCompleteStatus  task incomplete status
     */
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