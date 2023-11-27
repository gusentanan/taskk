package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.model.TaskkToDo
import java.time.LocalDate
import java.time.LocalTime

sealed class DetailEvent {

    data class Delete(val taskk: TaskkToDo): DetailEvent()
    data class OnToggleStatus(val taskk: TaskkToDo): DetailEvent()
    object ResetDueDate: DetailEvent()
    object ResetDueTime: DetailEvent()
    data class SelectDueDate(val date: LocalDate): DetailEvent()
    data class SelectDueTime(val time: LocalTime): DetailEvent()
    sealed class TaskkTitleEvent: DetailEvent() {
        object OnClickSaveCreate: TaskkTitleEvent()
        object OnClickSaveUpdate: TaskkTitleEvent()
        object OnShow: TaskkTitleEvent()
        data class ChangeTaskkTitle(val title: TextFieldValue): TaskkTitleEvent()

    }

    sealed class TaskkCategoryEvent: DetailEvent() {
        object OnShow: TaskkCategoryEvent()
        data class SelectCategory(val category: CategoryItems): TaskkCategoryEvent()


    }

    sealed class TaskkPriorityEvent: DetailEvent() {
        object OnShow: TaskkPriorityEvent()
        data class SelectPriority(val priority: PriorityItems): TaskkPriorityEvent()

    }

    sealed class TaskkRepeatableEvent: DetailEvent() {
        object OnShow: TaskkRepeatableEvent()
        data class SelectRepeatable(val repeat: RepeatableItems): TaskkRepeatableEvent()

    }

    sealed class TaskkNoteEvent: DetailEvent() {
        object OnClickSave: TaskkNoteEvent()
        object OnShow: TaskkNoteEvent()
        data class ChangeTaskkNote(val note: TextFieldValue): TaskkNoteEvent()

    }

}