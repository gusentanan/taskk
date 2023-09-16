package com.bagusmerta.taskk.utils

import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime


fun getMockListTask(): Flow<TaskkList>  = flow {
    emit(
        TaskkList(
            id = "001",
            mutableListOf(
                TaskkToDo(
                    id = "1",
                    name = "We go gym",
                    status = TaskkStatus.IN_PROGRESS,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our health",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.SELF_HELP,
                    taskkPriority = TaskkPriority.EASY
                ),
                TaskkToDo(
                    id = "2",
                    name = "We go grocery",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.MID
                ),
                TaskkToDo(
                    id = "3",
                    name = "Go to work",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "4",
                    name = "Go to work",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "5",
                    name = "Go to work",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "7",
                    name = "Go to work",
                    status = TaskkStatus.IN_PROGRESS,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "9",
                    name = "Go to work",
                    status = TaskkStatus.IN_PROGRESS,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "10",
                    name = "Go to work",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
                TaskkToDo(
                    id = "11",
                    name = "Go to work",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "For our Food",
                    noteUpdatedAt = null,
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
            )
        )
    )
}