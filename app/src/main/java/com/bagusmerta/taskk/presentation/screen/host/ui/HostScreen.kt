package com.bagusmerta.taskk.presentation.screen.host.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.presentation.designsystem.theme.TaskkTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun Host(content: @Composable () -> Unit) {
    val viewModel = hiltViewModel<HostViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    TaskkTheme(theme = state.themeState, content = content)
}