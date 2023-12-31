package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TskEmpty (
    text: String, modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxWidth()) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3F)
        )
    }
}