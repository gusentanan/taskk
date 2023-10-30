package com.bagusmerta.taskk.domain.model

data class TaskkDiff(
    val addedTaskk: Map<String, TaskkToDo>,
    val deletedTaskk: Map<String, TaskkToDo>,
    val modifiedTaskk: Map<String, TaskkToDo>,
)