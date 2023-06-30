package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray
import com.bagusmerta.taskk.presentation.designsystem.theme.gray20
import com.bagusmerta.taskk.utils.TaskkPriority


@Composable
fun TskItem(
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
    color: Color,
    tskTitle: String,
    tskDueDate: String,
    tskCategory: String,
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
               verticalAlignment = Alignment.Top,
                modifier = Modifier.padding(contentPadding)
            ){
                TskIconButton(
                    onClick = onCheckBoxClick,
                    shape = RectangleShape,
                    color = Color.Transparent
                ) {
                    TskIcon(
                        imageIcon = TaskkIcon.Check,
                        tintColor = color
                    )
                }

                Column(
                    modifier = Modifier.padding(4.dp).weight(1F)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        TskTitle(text = tskTitle)
                        Spacer(Modifier.width(10.dp))
                        TskPriority(priority = taskkPriority)
                    }
                    Row( modifier = Modifier.fillMaxWidth(),) {
                        TskCategory(text = tskCategory)
                        Spacer(Modifier.width(4.dp))
                        TskDueDate(text = tskDueDate)
                    }
                }
            }
            Divider(
                color = gray20,
                thickness = 1.dp,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            )
        }
    }
}


@Preview
@Composable
fun previewTskItem(){
    TskItem(
        onClick = { },
        onCheckBoxClick = {  },
        color = MaterialTheme.colorScheme.primary,
        tskTitle = "Do Leetcode Easy",
        tskDueDate = "Due ~ Fri, 27 June 2023",
        tskCategory = "More College",
        taskkPriority = TaskkPriority.EASY,
        contentPadding = PaddingValues(10.dp)
    )
}

@Composable
fun TskTitle(
    text: String,
    textColor: Color = Color.Unspecified
){
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(textAlign = TextAlign.Center),
            color = textColor
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
        TaskkPriority.EASY -> Color.Green
        TaskkPriority.MEDIUM -> Color.Yellow
        TaskkPriority.HARD -> Color.Red
    }
    Column(
        modifier = modifier.padding(top = 2.dp)
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
        priority = TaskkPriority.MEDIUM
    )
}
