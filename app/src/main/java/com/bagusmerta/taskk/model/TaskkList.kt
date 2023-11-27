package com.bagusmerta.taskk.model

data class TaskkList(
    val id: String = "",
    val tasks: List<TaskkToDo> = listOf()
)
