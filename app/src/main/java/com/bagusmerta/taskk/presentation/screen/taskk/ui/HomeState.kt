package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.domain.model.TaskkList
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.extensions.toTaskkListState
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl

@Immutable
data class HomeState(
    // This variable act as initial state value
    val taskkName: TextFieldValue = TextFieldValue(),
    val taskkList: TaskkList = TaskkList()
) {
    val todayDate = DateTimeProviderImpl().getNowDate()
    val listTaskkDisplayable = taskkList.toTaskkListState()
    val validTaskkIncomplete = listTaskkDisplayable.taskkItem.filterIsInstance<TaskkItem.InProgress>().size
    val validTaskkCompleted = listTaskkDisplayable.taskkItem.filterIsInstance<TaskkItem.Complete>().size

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