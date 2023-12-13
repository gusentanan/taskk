package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.FooterWithButton
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithSettingsButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddTaskClick: () -> Unit,
    onTaskItemClick: (String, String) -> Unit,
    onClickSettings: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val listState = rememberLazyListState()

    ListTaskkContent(
        tasks = state.listTaskkDisplayable.taskkItem,
        header = {
            HeaderHomeScreen(
                dateNow = state.todayDate,
                completedTaskkCounts = state.validTaskkCompleted,
                inCompleteTaskkCounts = state.validTaskkIncomplete,
                onClickSettings = onClickSettings
            )
        },
        onCheckBoxClick = { viewModel.dispatch(HomeEvent.TaskkEvent.OnToggleStatus(it)) } ,
        onClickTaskItem = { onTaskItemClick(it.id, 1.toString()) } ,
        onAddTaskClick = { onAddTaskClick() },
        listState = listState,
        onSwipeDelete = { viewModel.dispatch(HomeEvent.TaskkEvent.Delete(it)) }
    )
}

@Composable
fun HeaderHomeScreen(
    dateNow: LocalDateTime,
    completedTaskkCounts: Int,
    inCompleteTaskkCounts: Int,
    onClickSettings: () -> Unit,
){
    val taskStatusString: String = StringBuilder("$completedTaskkCounts Completed, $inCompleteTaskkCounts Incomplete").toString()

    HeaderWithSettingsButton(
        dateNow = dateNow.formatDateTime(),
        taskStatus = taskStatusString,
        onClickSettings = { onClickSettings() },

    )
}

@Composable
fun ListTaskkContent(
    tasks: List<TaskkItem>,
    header: @Composable ColumnScope.() -> Unit,
    onCheckBoxClick: (TaskkToDo) -> Unit,
    onClickTaskItem: (TaskkToDo) -> Unit,
    onAddTaskClick: () -> Unit,
    listState: LazyListState,
    onSwipeDelete: (TaskkToDo) -> Unit
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
                onClick = onClickTaskItem,
                onCheckBoxClick = onCheckBoxClick,
                listState = listState,
                onSwipeDelete = onSwipeDelete
            )

            FooterWithButton(
                onClick = { onAddTaskClick() },
                textButton = stringResource(R.string.create_new_taskk)
            )
        }

    }

}
