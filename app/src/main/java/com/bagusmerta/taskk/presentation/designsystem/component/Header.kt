package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray
import com.bagusmerta.taskk.presentation.designsystem.theme.gray20


@Composable
fun HeaderWithBackButton(
    text: String,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier) {
        Box(modifier = modifier
            .padding(start = 16.dp, top = 4.dp)
            .weight(0.2F)
            ){
                TskBackButton(onClick = onClickBack)
            }
        TskModalTitle(
            text = text,
            modifier = modifier.weight(0.6F)
        )
        Spacer(
            modifier
                .size(0.dp)
                .weight(0.2F)
        )
    }
}

@Preview
@Composable
fun previewHeaderBack(){
    HeaderWithBackButton(
        text = "Detail Task",
        onClickBack = {}
    )
}

@Composable
fun HeaderWithSettingsButton(
    dateNow: String,
    taskStatus: String,
    onClickSettings: () -> Unit,
    modifier: Modifier = Modifier
){
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ){
            Column(
                modifier = modifier
                    .padding(start = 12.dp, top = 12.dp)
                    .weight(1F)
            ) {
                TskModalTitleMain(text = dateNow)
                TskModalSubTitleMain(text = taskStatus)
            }

            Box(modifier = modifier.padding(end = 12.dp, top = 2.dp)
            ) {
                TskSettingsButton(onClick = onClickSettings)
            }
        }
        Divider(
            color = gray20,
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
        onClickSettings = {}
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
            style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
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


