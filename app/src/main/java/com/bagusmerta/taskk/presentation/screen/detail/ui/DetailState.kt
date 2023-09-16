package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.input.TextFieldValue
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.utils.wrapper.DateTimeProvider
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import javax.annotation.concurrent.Immutable

@Immutable
data class DetailState(
    val editTaskkTitle: TextFieldValue = TextFieldValue(),
    val editTaskkNote: TextFieldValue = TextFieldValue(),
    val taskk: TaskkToDo = TaskkToDo(
        createdAt = DateTimeProviderImpl().getNowDate(),
        updatedAt = DateTimeProviderImpl().getNowDate()
    )
){
    val validTaskkTitle = editTaskkTitle.text.isNotBlank()
    val validTaskkNote = editTaskkNote.text.isNotBlank()
}
