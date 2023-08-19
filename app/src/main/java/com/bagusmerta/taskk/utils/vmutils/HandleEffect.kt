package com.bagusmerta.taskk.utils.vmutils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope

@Composable
fun <STATE, EFFECT, EVENT, ENV> HandleEffect(
    viewModel: StateViewModel<STATE, EFFECT, EVENT, ENV>,
    handle: suspend CoroutineScope.(EFFECT) -> Unit
) {
    val effect by viewModel.effect.collectAsStateWithLifecycle()
    LaunchedEffect(effect) {
        effect?.let {
            handle(it)
            viewModel.resetEffect()
        }
    }
}