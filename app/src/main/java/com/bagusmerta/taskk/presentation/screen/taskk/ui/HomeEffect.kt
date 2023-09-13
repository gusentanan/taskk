package com.bagusmerta.taskk.presentation.screen.taskk.ui

sealed class HomeEffect {
    object ShowNewTaskInput : HomeEffect()
    data class ScrollTo(val position: Int) : HomeEffect()
    data class Relaunch(val listId: String) : HomeEffect()
}