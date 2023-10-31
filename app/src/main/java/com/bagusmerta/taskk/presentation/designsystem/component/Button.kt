package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.Shapes

@Composable
fun TskBackButton(
    onClick: () -> Unit,
    imageVector: ImageVector = TaskkIcon.ArrowBack
){
    TskIconButton(
        onClick = onClick,
        modifier = Modifier.size(42.dp),
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
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
fun TskInfoButton(
    onClick: () -> Unit,
    imageVector: ImageVector = TaskkIcon.Info
){
    TskIconButton(
        onClick = onClick,
        modifier = Modifier.size(42.dp),
        shape = CircleShape,
        color = MaterialTheme.colorScheme.surface
    ) {
        TskIcon(
            imageIcon = imageVector,
            modifier = Modifier.size(32.dp)
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

@Preview
@Composable
fun previewInfoButton(){
    TskInfoButton(onClick = {})
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