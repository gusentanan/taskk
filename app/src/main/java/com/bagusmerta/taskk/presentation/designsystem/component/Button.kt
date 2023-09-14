package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.Shapes
import com.bagusmerta.taskk.presentation.designsystem.theme.gray20
import com.bagusmerta.taskk.presentation.designsystem.theme.lPrimary
import kotlin.math.round

@Composable
fun TskBackButton(
    onClick: () -> Unit,
    imageVector: ImageVector = TaskkIcon.ArrowBack
){
    TskIconButton(
        onClick = onClick,
        modifier = Modifier.size(20.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface
    ) {
        TskIcon(imageIcon = imageVector)
    }
}

@Composable
fun TskSettingsButton(
    onClick: () -> Unit,
    imageVector: ImageVector = TaskkIcon.Settings
){
    TskIconButton(
        onClick = onClick,
        modifier = Modifier.size(42.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface
    ) {
        TskIcon(
            imageIcon = imageVector,
            modifier = Modifier.size(38.dp)
        )
    }
}

@Composable
fun TskIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape,
    color: Color = MaterialTheme.colorScheme.secondary,
    content: @Composable () -> Unit
){
    val shape = shape
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = color,
                shape = shape
            )
            .clip(shape),
        enabled = enabled
    ){
        content()
    }
}

@Preview
@Composable
fun previewBackButton(){
    TskBackButton(onClick = {})
}

@Preview
@Composable
fun previewSettingsButton(){
    TskSettingsButton(onClick = {})
}

@Composable
fun TskButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.height(42.dp),
        content = content,
        shape = Shapes.large,
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3F)
        )
    )
}

@Preview
@Composable
fun previewButton(){
    TskButton(onClick = {}) {
        Text(text = "Create")
    }
}

@Composable
fun TskSecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
){
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(42.dp),
        shape = Shapes.large,
        content = content,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    )
}

@Preview
@Composable
fun previewSecondaryButton(){
    TskSecondaryButton(onClick = {}) {
        Text(text = "Create")
    }
}