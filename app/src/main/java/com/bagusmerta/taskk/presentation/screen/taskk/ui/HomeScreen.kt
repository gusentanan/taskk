package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.glance.text.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.data.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithSettingsButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onRelaunchScreen: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskItemClick: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    ListTaskkContent(
        tasks = state.listTaskkDisplayable.taskkItem,
        header = {
            HeaderHomeScreen(
                dateNow = state.todayDate,
                completedTaskkCounts = state.validTaskkCompleted,
                inCompleteTaskkCounts = state.validTaskkIncomplete
            )
        },
        onCheckBoxClick = {  } ,
        onClickTaskItem = { } ,
        color = Color.Transparent,
        listState = listState
    )

}

@Composable
fun HeaderHomeScreen(
    dateNow: LocalDateTime,
    completedTaskkCounts: Int,
    inCompleteTaskkCounts: Int
){
    val taskStatusString: String = StringBuilder("$completedTaskkCounts Completed, $inCompleteTaskkCounts Incomplete").toString()

    HeaderWithSettingsButton(
        dateNow = dateNow.formatDateTime(),
        taskStatus = taskStatusString,
        onClickSettings = { }
    )
}

@Composable
fun ListTaskkContent(
    tasks: List<TaskkItem>,
    header: @Composable ColumnScope.() -> Unit,
    onCheckBoxClick: (TaskkToDo) -> Unit,
    onClickTaskItem: (TaskkToDo) -> Unit,
    color: Color,
    listState: LazyListState
){
    TskLayout {
        header()

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            TaskkContent(
                modifier = Modifier.weight(1F),
                tasks = tasks,
                color = color,
                onClick = onClickTaskItem,
                onCheckBoxClick = onCheckBoxClick,
                listState = listState
            )
            
        }

    }

}
