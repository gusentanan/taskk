package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.input.TextFieldValue
import java.time.LocalTime

sealed class DetailEvent {
    object OnClickSave: DetailEvent()
    object OnShow: DetailEvent()
    object OnToggleStatus: DetailEvent()
    object Delete: DetailEvent()

    data class ChangeTaskkTitle(val title: TextFieldValue): DetailEvent()
    data class SelectDueDate(val date: LocalTime): DetailEvent()
    data class SelectPriority(val priority: String): DetailEvent()
    data class SelectCategory(val category: String): DetailEvent()
    data class ChangeTaskkNote(val note: TextFieldValue): DetailEvent()

}