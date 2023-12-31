package com.bagusmerta.taskk.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.model.TaskkCategory
import com.bagusmerta.taskk.model.TaskkPriority
import com.bagusmerta.taskk.model.TaskkRepeat
import com.bagusmerta.taskk.model.TaskkStatus
import java.time.LocalDateTime

/**
 *
 *  The omnibus create/update/delete, also referred to as the last-write-wins strategy,
 *  involves setting the resource to the given value. If the provided value is null,
 *  the resource is deleted. In cases where two conflicting set operations occur concurrently,
 *  select the "latest" one as the winner, and optionally, return its previous value.
 */
@Dao
interface TaskkWriteDao {

    /**
     * Add a new task
     * @param taskks  entity model for Taskk
     */
    @Insert
    fun insertTaskk(taskks: TaskkTodoDb)

    /**
     * Delete a task by its ID
     * @param taskkId  ID of a task
     */
    @Query("DELETE FROM TaskkTodoDb WHERE taskk_id = :taskkId")
    fun deleteTaskkById(taskkId: String)

    /**
     * Edit Task title / name
     * @param taskkId  ID of a task
     * @param title  task title
     * @param updateAt  time stamp when task is updated
     */
    @Query("UPDATE TaskkTodoDb SET taskk_name = :title,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkTitle(taskkId: String, title: String, updateAt: LocalDateTime)

    /**
     * Edit Task note
     * @param taskkId  ID of a task
     * @param note  task note
     * @param updateAt  time stamp when task is updated
     */
    @Query("UPDATE TaskkTodoDb SET taskk_note = :note,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkNote(taskkId: String, note: String, updateAt: LocalDateTime)

    /**
     * Edit Task deadline
     * @param taskkId  ID of a task
     * @param dueDate  deadline set by user
     * @param updateAt  time stamp when task is updated
     * @param isDueDateSet  flag to check if deadline is already set
     */
    @Query("UPDATE TaskkTodoDb SET due_date = :dueDate, updated_at = :updateAt, is_due_date_set = :isDueDateSet WHERE taskk_id = :taskkId")
    fun updateTaskkDueDate(taskkId: String, dueDate: LocalDateTime?, updateAt: LocalDateTime, isDueDateSet: Boolean)

    /**
     * Reset Task deadline
     * @param taskkId  ID of a task
     * @param dueDate  deadline task set by user
     * @param updateAt  time stamp when task is updated
     * @param isDueDateSet  flag to check if deadline is already set
     */
    @Query("UPDATE TaskkTodoDb SET due_date = :dueDate, updated_at = :updateAt, is_due_date_set = :isDueDateSet WHERE taskk_id = :taskkId")
    fun resetTaskkDueDate(taskkId: String, dueDate: LocalDateTime?, updateAt: LocalDateTime, isDueDateSet: Boolean)

    /**
     * Edit Task priority (Easy, Medium, Hard)
     * @param taskkId  ID of a task
     * @param priority  priority of a task set by user
     * @param updateAt  time stamp when task is updated
     */
    @Query("UPDATE TaskkTodoDb SET taskk_priority = :priority,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkPriority(taskkId: String, priority: TaskkPriority, updateAt: LocalDateTime)

    /**
     * Edit task category (Work, Studies, etc)
     * @param taskkId  ID of a task
     * @param category  categoru of a task set by user
     * @param updateAt  time stamp when task is updated
     */
    @Query("UPDATE TaskkTodoDb SET taskk_category = :category, updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkCategory(taskkId: String, category: TaskkCategory, updateAt: LocalDateTime)

    /**
     * Edit task status (completed or incomplete)
     * @param status  status of a task set by user
     * @param taskkId  ID of a task
     * @param updateAt  time stamp when task is updated
     */
    @Query("UPDATE TaskkTodoDb SET taskk_status = :status, updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkStatus(status: TaskkStatus, taskkId: String, updateAt: LocalDateTime)

    /**
     * Edit task repeatable status (repeatable or not)
     * @param repeat  task repeatable status set by user
     * @param updateAt  time stamp when task is updated
     * @param taskkId  ID of a task
     */
    @Query("UPDATE TaskkTodoDb SET taskk_repeat = :repeat, updated_at  = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkRepeat(repeat: TaskkRepeat, updateAt: LocalDateTime, taskkId: String)

}