package com.bagusmerta.taskk.presentation.screen.host.ui

import com.bagusmerta.taskk.utils.themes.TaskkTheme
import javax.annotation.concurrent.Immutable

@Immutable
data class HostState(
    val themeState: TaskkTheme = TaskkTheme.SYSTEM
)
