package com.bagusmerta.taskk.utils.extensions

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent


// Animation related Extension

fun Modifier.onPositionInParentChanged(
    onChange: (LayoutCoordinates) -> Unit
) = composed {
    var lastPosition by remember { mutableStateOf(Offset.Zero) }
    Modifier.onGloballyPositioned { coordinates ->
        if (coordinates.positionInParent() != lastPosition) {
            lastPosition = coordinates.positionInParent()
            onChange(coordinates)
        }
    }
}
