package com.bagusmerta.taskk.presentation.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val SmallRadius = 4.dp
val MediumRadius = 8.dp
val LargeRadius = 16.dp

/**
 *  Shapes for any rounded corner component
 * */
val Shapes = Shapes(
    small = RoundedCornerShape(SmallRadius),
    medium = RoundedCornerShape(MediumRadius),
    large = RoundedCornerShape(LargeRadius)
)