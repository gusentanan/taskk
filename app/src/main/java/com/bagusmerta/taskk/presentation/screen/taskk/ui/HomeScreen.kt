package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.glance.text.Text
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.FooterWithButton
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithSettingsButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import java.time.LocalDateTime

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onRelaunchScreen: (String) -> Unit,
    onAddTaskClick: () -> Unit,
    onTaskItemClick: (String, String) -> Unit,
    onClickSettings: () -> Unit
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
        listState = listState
    )
}

@Composable
fun HeaderHomeScreen(
    dateNow: LocalDateTime,
    completedTaskkCounts: Int,
    inCompleteTaskkCounts: Int,
    onClickSettings: () -> Unit
){
    val taskStatusString: String = StringBuilder("$completedTaskkCounts Completed, $inCompleteTaskkCounts Incomplete").toString()

    HeaderWithSettingsButton(
        dateNow = dateNow.formatDateTime(),
        taskStatus = taskStatusString,
        onClickSettings = { onClickSettings() }
    )
}

@Composable
fun ListTaskkContent(
    tasks: List<TaskkItem>,
    header: @Composable ColumnScope.() -> Unit,
    onCheckBoxClick: (TaskkToDo) -> Unit,
    onClickTaskItem: (TaskkToDo) -> Unit,
    onAddTaskClick: () -> Unit,
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
                onClick = onClickTaskItem,
                onCheckBoxClick = onCheckBoxClick,
                listState = listState
            )

            FooterWithButton(
                onClick = { onAddTaskClick() },
                textButton = "Create a new Task"
            )
            Spacer(Modifier.height(10.dp))
        }

    }

}
