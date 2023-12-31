package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bagusmerta.taskk.model.TaskkPriority
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray
import com.bagusmerta.taskk.presentation.designsystem.theme.softGreen
import com.bagusmerta.taskk.presentation.designsystem.theme.softRed
import com.bagusmerta.taskk.presentation.designsystem.theme.softYellow
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.AlphaHigh
import androidx.compose.material3.Divider as MaterialDivider

@Composable
fun TskModalCell(
    onClick: () -> Unit,
    text: String,
    color: Color = MaterialTheme.colorScheme.surfaceVariant,
    textColor: Color = Color.Unspecified,
    enabled: Boolean = true,
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null
) {
    val colorAlpha = if (enabled) {
        AlphaHigh
    } else {
        AlphaDisabled
    }
    val onClickState = if (enabled) {
        onClick
    } else {
        { }
    }
    val indication = if (enabled) {
        LocalIndication.current
    } else {
        null
    }

    val shape = MaterialTheme.shapes.large
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(56.dp)
            .clip(shape)
            .clickable(
                onClick = onClickState,
                indication = indication,
                interactionSource = remember { MutableInteractionSource() }
            ),
        shape = shape,
        color = color.copy(alpha = colorAlpha),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leftIcon != null) {
                Spacer(Modifier.width(8.dp))
                leftIcon()
                Spacer(Modifier.width(16.dp))
            } else {
                Spacer(Modifier.width(20.dp))
            }

            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = textColor
            )

            if (rightIcon != null) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    rightIcon()
                    Spacer(Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun TskItem(
    modifier: Modifier,
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
    color: Color,
    tskTitle: String,
    tskDueDate: String,
    tskCategory: String,
    leftIcon: ImageVector,
    taskkPriority: TaskkPriority,
    contentPadding: PaddingValues,
    onSwipeDelete: () -> Unit
){
    TaskkSwipeToDismiss(
        modifier = modifier,
        backgroundModifier = Modifier.background(MaterialTheme.colorScheme.secondary),
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick),
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(contentPadding),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                        TskIconButton(
                            onClick = onCheckBoxClick,
                            shape = RectangleShape,
                            color = Color.Transparent
                        ) {
                            TskIcon(
                                imageIcon = leftIcon,
                                tintColor = color
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(4.dp)
                                .weight(1F)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                TskTitle(text = tskTitle)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                TskCategory(text = tskCategory)
                                TskDueDate(text = tskDueDate)
                            }
                        }

                        Spacer(Modifier.width(10.dp))
                        TskPriority(priority = taskkPriority)
                    }
                    MaterialDivider(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp, bottom = 10.dp)
                            .align(Alignment.Start),
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
        },
        onDismiss = { onSwipeDelete() }
    )
}

@Composable
fun TskItemDetail(
    modifier: Modifier,
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
    color: Color,
    tskTitle: String,
    leftIcon: ImageVector,
    taskkPriority: TaskkPriority,
    contentPadding: PaddingValues
){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(contentPadding),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                TskIconButton(
                    onClick = onCheckBoxClick,
                    shape = RectangleShape,
                    color = Color.Transparent
                ) {
                    TskIcon(
                        imageIcon = leftIcon,
                        tintColor = color
                    )
                }

                Column(modifier = Modifier
                    .padding(4.dp)
                    .weight(1F)) {
                    TskTitle(text = tskTitle)
                }

                Spacer(Modifier.width(10.dp))
                TskPriority(priority = taskkPriority)
            }
        }
    }
}



@Preview
@Composable
fun previewTskItem(){
    TskItem(
        modifier = Modifier,
        onClick = { },
        onCheckBoxClick = {  },
        color = MaterialTheme.colorScheme.primary,
        tskTitle = "do a coding job and interview a candidate for SWE junior position, do a coding job and interview a candidate for SWE junior position, and interview a candidate for SWE junior position, ",
        tskDueDate = "Due, Fri 27 June 2023",
        tskCategory = "More College",
        taskkPriority = TaskkPriority.EASY,
        contentPadding = PaddingValues(10.dp),
        leftIcon = TaskkIcon.Check,
        onSwipeDelete = { }
    )
}

@Preview
@Composable
fun previewTskItemDetail(){
    TskItemDetail(
        modifier = Modifier,
        onClick = { },
        onCheckBoxClick = {  },
        color = MaterialTheme.colorScheme.primary,
        tskTitle = "Do Leetcode Easy",
        taskkPriority = TaskkPriority.EASY,
        contentPadding = PaddingValues(10.dp),
        leftIcon = TaskkIcon.Check
    )
}

@Composable
fun TskTitle(
    text: String,
    textColor: Color = Color.Unspecified
){
    Column {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Justify),
            color = textColor,
            maxLines = 4,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun TskCategory(
    text: String,
    textColor: Color = commonGray
){
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
            color = textColor
        )
    }
}

@Composable
fun TskDueDate(
    text: String,
    textColor: Color = Color.Unspecified
){
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall.copy(textAlign = TextAlign.Center),
            color = textColor
        )
    }
}

@Composable
fun TskPriority(
    modifier: Modifier = Modifier,
    priority: TaskkPriority
){
    val boxColor = when(priority){
        TaskkPriority.EASY -> softGreen
        TaskkPriority.MID -> softYellow
        TaskkPriority.HARD -> softRed
    }
    Column(
        modifier = modifier.padding(all = 10.dp)
    ) {
        Box(modifier = modifier
            .width(60.dp)
            .height(14.dp)
            .background(
                color = boxColor,
                shape = MaterialTheme.shapes.large
            )
        )
    }
}

@Preview
@Composable
fun previewPriority(){
    TskPriority(
        modifier = Modifier,
        priority = TaskkPriority.MID
    )
}
