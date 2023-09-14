package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.data.model.TaskkList
import com.bagusmerta.taskk.data.model.TaskkStatus
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkItem
import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkListState


fun TaskkList.toTaskkListState(): TaskkListState{
    val tasks = tasks.map {
        when(it.status){
            TaskkStatus.COMPLETE -> TaskkItem.Complete(it)
            TaskkStatus.IN_PROGRESS -> TaskkItem.InProgress(it)
        }
    }.sortedBy {
        it is TaskkItem.Complete
    }.toMutableList()

    val firstCompletedIndex = tasks.indexOfFirst { it is TaskkItem.Complete }
    if(firstCompletedIndex != -1){
        tasks.add(firstCompletedIndex, TaskkItem.CompleteHeader())
    }

    return TaskkListState(
        id = id,
        taskkItem = tasks
    )
}