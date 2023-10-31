package com.bagusmerta.taskk.utils.mapper

import com.bagusmerta.taskk.data.local.entity.TaskkTodoDb
import com.bagusmerta.taskk.domain.model.TaskkToDo

fun List<TaskkTodoDb>.toTaskkTodo(): List<TaskkToDo> {
    return map { it.toTaskkTodo() }
}

fun TaskkTodoDb.toTaskkTodo(): TaskkToDo {
    return TaskkToDo(
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