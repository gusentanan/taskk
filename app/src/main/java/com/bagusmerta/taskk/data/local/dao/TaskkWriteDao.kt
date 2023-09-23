package com.bagusmerta.taskk.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkPriority
import java.time.LocalDate

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
    fun insertTaskk(taskkId: String)

    @Query("DELETE FROM TaskkTodoDb WHERE taskk_id = :taskkId")
    fun deleteTaskkById(taskkId: String)

    @Query("UPDATE TaskkTodoDb SET taskk_name = :title WHERE taskk_id = :taskkId")
    fun updateTaskkTitle(taskkId: String, title: String)

    @Query("UPDATE TaskkTodoDb SET taskk_note = :note WHERE taskk_id = :taskkId")
    fun updateTaskkNote(taskkId: String, note: String)

    @Query("UPDATE TaskkTodoDb SET due_date = :dueDate WHERE taskk_id = :taskkId")
    fun updateTaskkDueDate(taskkId: String, dueDate: LocalDate)

    @Query("UPDATE TaskkTodoDb SET taskk_priority = :priority WHERE taskk_id = :taskkId")
    fun updateTaskkPriority(taskkId: String, priority: TaskkPriority)

    @Query("UPDATE TaskkTodoDb SET taskk_category = :category WHERE taskk_id = :taskkId")
    fun updateTaskkCategory(taskkId: String, category: TaskkCategory)
}