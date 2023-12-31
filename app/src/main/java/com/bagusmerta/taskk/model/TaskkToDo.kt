package com.bagusmerta.taskk.model

import java.time.LocalDateTime

data class TaskkToDo(
    val id: String = "",
    val name: String = "",
    val status: TaskkStatus = TaskkStatus.IN_PROGRESS,
    val completedAt: LocalDateTime? = null,
    val dueDate: LocalDateTime? = null,
    val isDueDateTimeSet: Boolean = false,
    val note: String = "",
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val taskkPriority: TaskkPriority = TaskkPriority.EASY,
    val taskkCategory: TaskkCategory = TaskkCategory.SELF_HELP,
    val taskkRepeat: TaskkRepeat = TaskkRepeat.NEVER
)
