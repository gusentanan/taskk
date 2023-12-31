package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.TskEmpty
import com.bagusmerta.taskk.presentation.designsystem.component.TskItem
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import com.bagusmerta.taskk.utils.extensions.identifier
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TaskkContent(
    modifier: Modifier,
    tasks: List<TaskkItem>,
    onClick: (TaskkToDo) -> Unit,
    onSwipeDelete: (TaskkToDo) -> Unit,
    onCheckBoxClick: (TaskkToDo) -> Unit,
    listState: LazyListState
) {
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = listState
    ) {
        if (tasks.isEmpty()){
            item {
                TskEmpty(text = stringResource(R.string.taskk_empty_text),
                    modifier = Modifier
                        .fillParentMaxHeight()
                        .padding(bottom = 100.dp)
                )
            }
        } else {
            items(
                items = tasks,
                key = { item -> item.identifier() },
            ) {
                when(it){
                    is TaskkItem.CompleteHeader -> {
                        Spacer(Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .fillMaxWidth()
                                .fillMaxWidth()
                                .height(32.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.task_complete_header),
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }
                    is TaskkItem.Complete -> { 
                        TskItem(
                            modifier = Modifier.animateItemPlacement(),
                            onClick = { onClick(it.taskk) },
                            onCheckBoxClick = { onCheckBoxClick(it.taskk) },
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = AlphaDisabled),
                            tskTitle = it.taskk.name,
                            tskDueDate = it.taskk.dueDate?.formatDateTime() ?: stringResource(R.string.taskk_add_due_date_empty) ,
                            tskCategory = stringResource(it.taskk.taskkCategory.str),
                            taskkPriority = it.taskk.taskkPriority,
                            contentPadding = PaddingValues(all = 8.dp),
                            leftIcon = TaskkIcon.Check,
                            onSwipeDelete = { onSwipeDelete(it.taskk) }
                        )
                    }
                    is TaskkItem.InProgress -> {
                        var isChecked by remember { mutableStateOf(false) }
                        var debounceJob: Job? by remember { mutableStateOf(null) }

                        TskItem(
                            modifier = Modifier.animateItemPlacement(),
                            onClick = { onClick(it.taskk) },
                            onCheckBoxClick = {
                                isChecked = !isChecked
                                debounceJob?.cancel()
                                if(isChecked){
                                    debounceJob = coroutineScope.launch {
                                        delay(500)
                                        onCheckBoxClick(it.taskk)
                                    }
                                }
                            },
                            color = MaterialTheme.colorScheme.onBackground,
                            tskTitle = it.taskk.name,
                            tskDueDate = it.taskk.dueDate?.formatDateTime() ?: stringResource(R.string.taskk_add_due_date_empty),
                            tskCategory = stringResource(it.taskk.taskkCategory.str),
                            leftIcon = if(isChecked) {
                                TaskkIcon.Check
                            } else {
                                TaskkIcon.UnChecked
                            },
                            taskkPriority = it.taskk.taskkPriority,
                            contentPadding =  PaddingValues(all = 8.dp),
                            onSwipeDelete = { onSwipeDelete(it.taskk) }
                        )
                    }
                }
            }

        }

        item {
            Spacer(Modifier.height(250.dp))
        }
    }
}