package com.bagusmerta.taskk.presentation.screen.taskk.ui

import com.bagusmerta.taskk.model.TaskkToDo

sealed class HomeEvent {

    sealed class TaskkEvent: HomeEvent() {
        data class Delete(val task: TaskkToDo) : TaskkEvent()
        data class OnToggleStatus(val task: TaskkToDo) : TaskkEvent()
    }
}