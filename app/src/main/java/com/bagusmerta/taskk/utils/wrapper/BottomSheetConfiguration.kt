package com.bagusmerta.taskk.utils.wrapper

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import com.bagusmerta.taskk.presentation.designsystem.theme.LargeRadius
import com.bagusmerta.taskk.presentation.designsystem.theme.SmallRadius
import javax.annotation.concurrent.Immutable


@Immutable
data class BottomSheetConfiguration(
    val sheetShape: Shape,
    val showScrim: Boolean
)

val DefaultBottomSheet = BottomSheetConfiguration(
    RoundedCornerShape(
        topStart = LargeRadius,
        topEnd = LargeRadius
    ),
    true
)
