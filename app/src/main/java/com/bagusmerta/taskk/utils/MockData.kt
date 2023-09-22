package com.bagusmerta.taskk.utils

import android.util.Log
import com.bagusmerta.taskk.domain.model.TaskkCategory
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkPriority
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest
import java.time.LocalDateTime

fun getTaskkByIdMock(taskkId: String): Flow<TaskkToDo>  = flow {
    val data = getMockListTask().first()
    val dataMapped = mapTaskkListToTaskkTodo(data)

    dataMapped.forEach{
        if(it.id == taskkId){
            Log.d("BagusMertaS", it.toString())
            emit(it)
        }
    }
}

fun mapTaskkListToTaskkTodo(data: TaskkList): List<TaskkToDo> {
    val res = mutableListOf<TaskkToDo>()
    data.tasks.forEach {
        res.add(it)
    }
    return res
}


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
                    dueDate =  null,
                    isDueDateTimeSet = false,
                    note = "For our health",
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
                    isDueDateTimeSet = true,
                    note = "For our Food",
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.MID
                ),
                TaskkToDo(
                    id = "3",
                    name = "Go to work, do a coding job and a coding job and",
                    status = TaskkStatus.COMPLETE,
                    completedAt = null,
                    dueDate =  LocalDateTime.now(),
                    isDueDateTimeSet = false,
                    note = "do a coding job and interview a candidate for SWE junior position, do a coding job and interview a candidate for SWE junior position, and interview a candidate for SWE junior position, ",
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
                    createdAt = LocalDateTime.now(),
                    updatedAt = LocalDateTime.MAX,
                    taskkCategory = TaskkCategory.HOUSEHOLD,
                    taskkPriority = TaskkPriority.HARD
                ),
            )
        )
    )
}