package com.bagusmerta.taskk.utils.mapper

import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.model.TaskkToDo


fun TaskkToDo.toTaskkTodoDb(): TaskkTodoDb {
    return TaskkTodoDb(
        id,
        name,
        status,
        completedAt,
        dueDate,
        isDueDateTimeSet,
        note,
        createdAt,
        updatedAt,
        taskkPriority,
        taskkCategory,
        taskkRepeat
    )
}