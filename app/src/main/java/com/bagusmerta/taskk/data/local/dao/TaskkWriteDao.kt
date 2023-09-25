package com.bagusmerta.taskk.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import java.time.LocalDate
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

    @Insert
    fun insertTaskk(taskks: TaskkTodoDb)

    @Query("DELETE FROM TaskkTodoDb WHERE taskk_id = :taskkId")
    fun deleteTaskkById(taskkId: String)

    @Query("UPDATE TaskkTodoDb SET taskk_name = :title,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkTitle(taskkId: String, title: String, updateAt: LocalDateTime)

    @Query("UPDATE TaskkTodoDb SET taskk_note = :note,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkNote(taskkId: String, note: String, updateAt: LocalDateTime)

    @Query("UPDATE TaskkTodoDb SET due_date = :dueDate, updated_at = :updateAt, is_due_date_set = :isDueDateSet WHERE taskk_id = :taskkId")
    fun updateTaskkDueDate(taskkId: String, dueDate: LocalDateTime?, updateAt: LocalDateTime, isDueDateSet: Boolean)

    @Query("UPDATE TaskkTodoDb SET due_date = :dueDate, updated_at = :updateAt, is_due_date_set = :isDueDateSet WHERE taskk_id = :taskkId")
    fun resetTaskkDueDate(taskkId: String, dueDate: LocalDateTime?, updateAt: LocalDateTime, isDueDateSet: Boolean)

    @Query("UPDATE TaskkTodoDb SET taskk_priority = :priority,  updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkPriority(taskkId: String, priority: TaskkPriority, updateAt: LocalDateTime)

    @Query("UPDATE TaskkTodoDb SET taskk_category = :category, updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkCategory(taskkId: String, category: TaskkCategory,  updateAt: LocalDateTime)

    @Query("UPDATE TaskkTodoDb SET taskk_status = :status, updated_at = :updateAt WHERE taskk_id = :taskkId")
    fun updateTaskkStatus(status: TaskkStatus, taskkId: String, updateAt: LocalDateTime)

}