package com.bagusmerta.taskk.domain.model

data class TaskkList(
    val id: String = "",
    val tasks: List<TaskkToDo> = listOf()
)
