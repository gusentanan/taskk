package com.bagusmerta.taskk.presentation.screen.detail.ui

sealed class DetailEffect {
    object ShowCreateTaskkNameInput : DetailEffect()
    data class RefreshScreen(val taskkId: String) : DetailEffect()
    object OnClosePage: DetailEffect()
}