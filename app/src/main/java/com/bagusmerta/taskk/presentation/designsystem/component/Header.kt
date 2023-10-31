package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray


@Composable
fun HeaderWithBackButton(
    text: String,
    onClickBack: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier.padding(top = 10.dp)) {
        Box(modifier = modifier
            .padding(start = 16.dp)
            .weight(0.2F)
            ){
                TskBackButton(onClick = onClickBack)
            }
        TskModalTitle(
            text = text,
            modifier = modifier.weight(0.6F).padding(top = 6.dp)
        )
        TskIconButton(
            onClick = onClickDelete,
            color = Color.Transparent,
            modifier = Modifier
                .size(42.dp)
                .weight(0.2F),
            shape = CircleShape
        ) {
            TskIcon(
                imageIcon = TaskkIcon.Trash,
                modifier = Modifier.size(30.dp)
            )
        }
    }
}

@Preview
@Composable
fun previewHeaderBack(){
    HeaderWithBackButton(
        text = "Detail Task",
        onClickBack = {},
        onClickDelete = {}
    )
}

@Composable
fun HeaderWithSettingsButton(
    dateNow: String,
    taskStatus: String,
    onClickSettings: () -> Unit,
    onClickInfo: () -> Unit,
    modifier: Modifier = Modifier
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            Column(
                modifier = modifier
                    .padding(start = 20.dp, top = 12.dp)
                    .weight(1F)
            ) {
                TskModalTitleMain(text = dateNow)
                TskModalSubTitleMain(text = taskStatus)
            }

            Box(modifier = modifier.padding(end = 20.dp, top = 12.dp)
            ) {
                TskInfoButton(onClick = onClickInfo)
            }

            Box(modifier = modifier.padding(end = 20.dp, top = 12.dp)
            ) {
                TskSettingsButton(onClick = onClickSettings)
            }
        }
        Divider(
            color = commonGray,
            thickness = 1.dp,
            modifier = Modifier.padding(start = 14.dp, end =14.dp, top = 10.dp)
        )
    }
}

@Preview
@Composable
fun previewHeaderSettings(){
    HeaderWithSettingsButton(
        dateNow = "March 9, 2023",
        taskStatus = "5 Completed, 2 Incomplete",
        onClickSettings = {},
        onClickInfo = {},
    )
}

@Composable
fun TskModalTitle(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),
            color = textColor,
        )
    }
}

@Composable
fun TskModalTitleMain(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified
) {
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),
            color = textColor,
        )
    }
}

@Composable
fun TskModalSubTitleMain(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = commonGray
){
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxWidth()
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
            color = textColor
        )
    }
}


