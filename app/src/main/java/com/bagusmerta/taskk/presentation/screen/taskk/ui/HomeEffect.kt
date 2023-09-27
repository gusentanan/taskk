package com.bagusmerta.taskk.presentation.screen.taskk.ui

sealed class HomeEffect {
    data class ScrollTo(val position: Int) : HomeEffect()
}