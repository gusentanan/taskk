package com.bagusmerta.taskk.data.model

data class TaskkList(
    val id: String = "",
    val tasks: List<TaskkToDo> = listOf()
)
