package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.domain.model.TaskkToDo

sealed class HomeEvent {

    sealed class TaskkEvent: HomeEvent() {
        object CreateTask: TaskkEvent()
        object ClickTaskDone: TaskkEvent()
        object ClickSubmit: TaskkEvent()
        object OnShow: TaskkEvent()
        data class Delete(val task: TaskkToDo) : TaskkEvent()
        data class OnToggleStatus(val task: TaskkToDo) : TaskkEvent()
        data class ChangeTaskName(val name: TextFieldValue) : TaskkEvent()
    }
}