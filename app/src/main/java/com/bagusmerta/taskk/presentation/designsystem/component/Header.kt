package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray
import androidx.compose.material3.Divider as MaterialDivider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderWithNav(
    text: String,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Box {
        CenterAlignedTopAppBar(
            modifier = Modifier.padding(start = 4.dp),
            title = { TskModalTitle(text = text) },
            navigationIcon = {
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface
            ),
        )
        MaterialDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeaderWithNavAction(
    text: String,
    onClickBack: () -> Unit,
    onClickDelete: () -> Unit,
    modifier: Modifier = Modifier
){
    Box {
        CenterAlignedTopAppBar(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp),
            title = {
                TskModalTitle(
                    text = text,
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = { onClickBack() }
                ) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                TskIconButton(
                    onClick = onClickDelete,
                    color = Color.Transparent,
                    shape = CircleShape
                ) {
                    TskIcon(
                        imageIcon = TaskkIcon.Trash,
                        modifier = Modifier.size(30.dp)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
                titleContentColor = MaterialTheme.colorScheme.onSurface,
                actionIconContentColor = MaterialTheme.colorScheme.onSurface
            ),
        )
        MaterialDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart),
            color = MaterialTheme.colorScheme.inversePrimary
        )
    }
}

@Preview
@Composable
fun previewHeaderBack(){
    HeaderWithNavAction(
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
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.padding(bottom = 10.dp)
        ){
            Column(
                modifier = modifier
                    .padding(start = 20.dp, top = 12.dp)
                    .weight(1F)
            ) {
                TskModalTitleMain(text = dateNow)
                TskModalSubTitleMain(text = taskStatus)
            }

            Box(modifier = modifier.padding(end = 12.dp, top = 12.dp)){
                TskOverflowMenu {
                    onClickSettings()
                }
            }
        }
        MaterialDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Start),
            color = MaterialTheme.colorScheme.inversePrimary
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


