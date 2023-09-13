package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.data.model.TaskkList
import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.utils.extensions.toTaskkListState
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import java.time.LocalDateTime

@Immutable
data class HomeState(
    // This variable act as initial state value
    val taskkIncomplete: Int = 0,
    val taskkCompleted: Int = 0,
    val taskkName: TextFieldValue = TextFieldValue(),
    val taskkList: TaskkList = TaskkList()
) {
    val todayDate = DateTimeProviderImpl().getNowDate()
    val listTaskkDisplayable = taskkList.toTaskkListState()
    val validTaskkName = taskkName.text.isNotBlank()
    val validTaskkIncomplete = taskkIncomplete
    val validTaskkCompleted =- taskkCompleted

}

data class TaskkListState(
    val id: String = "",
    val taskkItem: List<TaskkItem> = listOf()
)

sealed class TaskkItem {
    data class CompleteHeader(val id: String = "CompleteHeader") : TaskkItem()

    data class Complete(
        val taskk: TaskkToDo
    ) : TaskkItem()

    data class InProgress(
        val taskk: TaskkToDo
    ) : TaskkItem()
}