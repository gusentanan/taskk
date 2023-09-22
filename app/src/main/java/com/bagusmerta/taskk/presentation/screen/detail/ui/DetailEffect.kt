package com.bagusmerta.taskk.presentation.screen.detail.ui

sealed class DetailEffect {
    data class ScrollTo(val position: Int): DetailEffect()
}