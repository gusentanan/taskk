package com.bagusmerta.taskk.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.bagusmerta.taskk.model.TaskkCategory
import com.bagusmerta.taskk.model.TaskkPriority
import com.bagusmerta.taskk.model.TaskkRepeat
import com.bagusmerta.taskk.model.TaskkStatus
import java.time.LocalDateTime

/**
 * Entity that represent taskk data for DB transaction
 */
@Entity(
    indices = [
        Index("taskk_name", unique = true)
    ]
)
data class TaskkTodoDb(
    @PrimaryKey
    @ColumnInfo("taskk_id")
    val id: String,

    @ColumnInfo("taskk_name")
    val name: String,

    @ColumnInfo("taskk_status")
    val status: TaskkStatus = TaskkStatus.IN_PROGRESS,

    @ColumnInfo("comepleted_at")
    val completedAt: LocalDateTime? = null,

    @ColumnInfo("due_date")
    val dueDate: LocalDateTime? = null,

    @ColumnInfo("is_due_date_set")
    val isDueDateTimeSet: Boolean = false,

    @ColumnInfo("taskk_note")
    val note: String,

    @ColumnInfo("created_at")
    val createdAt: LocalDateTime,

    @ColumnInfo("updated_at")
    val updatedAt: LocalDateTime,

    @ColumnInfo("taskk_priority")
    val taskkPriority: TaskkPriority = TaskkPriority.EASY,

    @ColumnInfo("taskk_category")
    val taskkCategory: TaskkCategory = TaskkCategory.SELF_HELP,

    @ColumnInfo("taskk_repeat")
    val taskkRepeat: TaskkRepeat = TaskkRepeat.NEVER
)