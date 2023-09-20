package com.bagusmerta.taskk.utils.extensions

import androidx.compose.ui.focus.FocusRequester
import kotlinx.coroutines.delay

suspend fun FocusRequester.requestFocusIme() {
    delay(100)
    requestFocus()
}
