package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun TskIcon(
    modifier: Modifier = Modifier,
    imageIcon: ImageVector,
    tintColor: Color = LocalContentColor.current
){
    Icon(
        imageVector = imageIcon,
        tint = tintColor,
        modifier = modifier,
        contentDescription = "TaskkIcon"
    )
}