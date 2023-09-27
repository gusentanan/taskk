package com.bagusmerta.taskk.presentation.screen.detail.ui


import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.EditCalendar
import androidx.compose.material.icons.rounded.LibraryBooks
import androidx.compose.material.icons.rounded.PriorityHigh
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.FooterWithText
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithBackButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskIcon
import com.bagusmerta.taskk.presentation.designsystem.component.TskItemDetail
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.MediumRadius
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.AlphaMedium
import com.bagusmerta.taskk.utils.DividerAlpha
import com.bagusmerta.taskk.utils.extensions.displayable
import com.bagusmerta.taskk.utils.extensions.dueDateDisplayable
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import com.bagusmerta.taskk.utils.extensions.isDueDateSet
import com.bagusmerta.taskk.utils.extensions.isExpired
import com.bagusmerta.taskk.utils.extensions.showDatePicker
import com.bagusmerta.taskk.utils.vmutils.HandleEffect
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBackPress: () -> Unit,
    onRefreshScreen: (String, String) -> Unit,
    onClosePage: () -> Unit,
    showCreateTaskkName: () -> Unit,
    onClickTaskkTitle: () -> Unit,
    onClickTaskkPriority: () -> Unit,
    onClickTaskkCategory: () -> Unit,
    onClickTaskkNote: () -> Unit,
    onClickTaskkDelete: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val activity = LocalContext.current as AppCompatActivity
    val listState = rememberLazyListState()

    HandleEffect(viewModel = viewModel) {
        when (it) {
            is DetailEffect.ShowCreateTaskkNameInput -> {
                showCreateTaskkName()
            }
            is DetailEffect.RefreshScreen -> {
                onRefreshScreen(it.taskkId, 1.toString())
            }
            is DetailEffect.OnClosePage -> {
                onClosePage()
            }
        }
    }

    DetailTaskkScreen(
        header = {
            HeaderWithBackButton(
                text = stringResource(R.string.detail_taskk_header),
                onClickBack = onBackPress,
                onClickDelete = {
                    viewModel.dispatch(DetailEvent.Delete(state.taskk))
                    onClickTaskkDelete()
                }
            )
        },
        taskk = state.taskk,
        note = state.taskk.note,
        dueDateTitle = state.taskk.dueDate?.formatDateTime() ?: stringResource(R.string.taskk_add_due_date),
        onClickTaskkTitle = { onClickTaskkTitle() },
        onClickDueDate = {
            val dueDateValue = state.taskk.dueDate?.toLocalDate()
            if (dueDateValue != null) {
                activity.showDatePicker(dueDateValue) { selectedDate ->
                    viewModel.dispatch(DetailEvent.SelectDueDate(selectedDate))
                }
            }
        },
        onCheckedChangeDueDate = { check ->
            if (check) {
                activity.showDatePicker(
                    DateTimeProviderImpl().getNowDate().toLocalDate()
                ) { selectedDate ->
                    viewModel.dispatch(DetailEvent.SelectDueDate(selectedDate))
                }
            } else {
                viewModel.dispatch(DetailEvent.ResetDueDate)
            }
        },
        onClickTaskkStatus = {
            viewModel.dispatch(DetailEvent.OnToggleStatus(state.taskk))
        },
        onClickTaskkPriority = { onClickTaskkPriority() },
        onClickTaskkCategory = { onClickTaskkCategory() },
        listState = listState,
        onClickTaskkNote = { onClickTaskkNote() }
    )

}

@Composable
fun DetailTaskkScreen(
    header: @Composable ColumnScope.() -> Unit,
    taskk: TaskkToDo,
    note: String,
    dueDateTitle: String,
    onClickTaskkTitle: () -> Unit,
    onClickDueDate: () -> Unit,
    onCheckedChangeDueDate: (Boolean) -> Unit,
    onClickTaskkPriority: () -> Unit,
    onClickTaskkCategory: () -> Unit,
    onClickTaskkStatus: () -> Unit,
    onClickTaskkNote: () -> Unit,
    listState: LazyListState
){
    TskLayout {
        header()
        Spacer(Modifier.height(20.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp)
                .weight(1F),
            state = listState
        ){
            item {
                // Taskk Item that show current item status (title, priority, etc)
                TskItemWrapper(
                    item = taskk,
                    onClick =  onClickTaskkTitle,
                    onCheckBoxClick = onClickTaskkStatus
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                // Taskk Priority section
                ActionCell(
                    title = stringResource(R.string.detail_taskk_add_priority_text),
                    shape = RoundedCornerShape(size = MediumRadius),
                    iconBgColor = MaterialTheme.colorScheme.secondary,
                    leftIcon = Icons.Rounded.PriorityHigh,
                    showDivider = true,
                    onClick = onClickTaskkPriority,
                    trailing = {
                        Row {
                            Text(
                                text = taskk.taskkPriority.name,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
                            )
                            Spacer(Modifier.width(8.dp))
                            TskIcon(
                                imageIcon = Icons.Rounded.ChevronRight,
                                tintColor = LocalContentColor.current.copy(alpha = AlphaDisabled)
                            )
                        }
                    }
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                //Taskk Category section
                ActionCell(
                    title = stringResource(R.string.detail_taskk_add_category_text),
                    shape = RoundedCornerShape(size = MediumRadius),
                    iconBgColor = MaterialTheme.colorScheme.secondary,
                    leftIcon = Icons.Rounded.LibraryBooks,
                    showDivider = true,
                    onClick = onClickTaskkCategory,
                    trailing = {
                        Row {
                            Text(
                                text = stringResource(taskk.taskkCategory.displayable()),
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal),
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
                            )
                            Spacer(Modifier.width(8.dp))
                            TskIcon(
                                imageIcon = Icons.Rounded.ChevronRight,
                                tintColor = LocalContentColor.current.copy(alpha = AlphaDisabled)
                            )
                        }
                    }
                )
            }

            item { Spacer(Modifier.height(8.dp)) }

            item {
                // Due Date section
                ActionCell(
                    title = dueDateTitle,
                    shape = RoundedCornerShape(size = MediumRadius),
                    iconBgColor = MaterialTheme.colorScheme.error,
                    leftIcon = Icons.Rounded.EditCalendar,
                    showDivider = true,
                    onClick = if(taskk.isDueDateSet()){
                        onClickDueDate
                    } else { null
                    },
                    trailing = {
                        Switch(
                            checked = taskk.isDueDateSet(),
                            onCheckedChange = onCheckedChangeDueDate,
                        )
                    },
                    titleColor = if (taskk.isExpired()) {
                        MaterialTheme.colorScheme.error
                    } else {
                        Color.Unspecified
                    }
                )
            }

            item { Spacer(Modifier.height(10.dp)) }

            item {
                // Taskk Note section
                val shape = RoundedCornerShape(size = MediumRadius)
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .clip(shape)
                        .clickable(onClick = onClickTaskkNote),
                    shape = shape,
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Column(
                        modifier = Modifier.padding(all = 16.dp)
                    ) {
                        if (note.isBlank()) {
                            Text(
                                text = stringResource(R.string.detail_taskk_add_note_text),
                                style = MaterialTheme.typography.titleSmall,
                            )
                        } else {
                            Text(
                                text = note,
                                style = MaterialTheme.typography.titleSmall,
                                maxLines = 6,
                                overflow = TextOverflow.Ellipsis
                            )

                            Spacer(Modifier.size(8.dp))

                            Text(
                                text = stringResource( R.string.taskk_add_note),
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaMedium)
                            )
                        }
                    }
                }
            }
        }
        FooterWithText(
            textFooter = taskk.dueDateDisplayable(LocalContext.current.resources) ?: stringResource(
                R.string.taskk_add_due_date_footer_empty
            )
        )

        Spacer(Modifier.height(10.dp))
    }

}


@Composable
fun TskItemWrapper(
    item: TaskkToDo,
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    when(item.status){
        TaskkStatus.COMPLETE -> {
            TskItemDetail(
                modifier = Modifier,
                onClick = { onClick() },
                onCheckBoxClick = { onCheckBoxClick() },
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = AlphaDisabled),
                tskTitle = item.name,
                taskkPriority = item.taskkPriority,
                contentPadding = PaddingValues(all = 8.dp),
                leftIcon = TaskkIcon.Check
            )
        }
        TaskkStatus.IN_PROGRESS -> {
            var isChecked by remember { mutableStateOf(false) }
            var debounceJob: Job? by remember { mutableStateOf(null) }

            TskItemDetail(
                modifier = Modifier,
                onClick = { onClick() },
                onCheckBoxClick = {
                    isChecked = !isChecked
                    debounceJob?.cancel()
                    if(isChecked){
                        debounceJob = coroutineScope.launch {
                            delay(500)
                            onCheckBoxClick()
                        }
                    }
                },
                color = MaterialTheme.colorScheme.onBackground,
                tskTitle = item.name,
                leftIcon = if(isChecked) {
                    TaskkIcon.Check
                } else {
                    TaskkIcon.UnChecked
                },
                taskkPriority = item.taskkPriority,
                contentPadding =  PaddingValues(all = 8.dp)
            )
        }
    }
}

@Composable
private fun ActionCell(
    title: String,
    titleColor: Color = Color.Unspecified,
    shape: Shape,
    iconBgColor: Color,
    leftIcon: ImageVector,
    showDivider: Boolean,
    onClick: (() -> Unit)? = null,
    trailing: @Composable () -> Unit
) {
    if (onClick != null) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .clip(shape)
                .clickable(onClick = onClick),
            shape = shape,
            color = MaterialTheme.colorScheme.surface
        ) {
            ActionContentCell(
                title = title,
                titleColor = titleColor,
                iconBgColor = iconBgColor,
                leftIcon = leftIcon,
                showDivider = showDivider,
                trailing = trailing
            )
        }
    } else {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
                .clip(shape),
            shape = shape,
            color = MaterialTheme.colorScheme.surface
        ) {
            ActionContentCell(
                title = title,
                titleColor = titleColor,
                iconBgColor = iconBgColor,
                leftIcon = leftIcon,
                showDivider = showDivider,
                trailing = trailing
            )
        }
    }
}

@Composable
private fun ActionContentCell(
    title: String,
    titleColor: Color,
    iconBgColor: Color,
    leftIcon: ImageVector,
    showDivider: Boolean,
    trailing: @Composable () -> Unit
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(all = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .background(shape = RoundedCornerShape(size = 6.dp), color = iconBgColor),
                contentAlignment = Alignment.Center
            ) {
                TskIcon(
                    imageIcon = leftIcon,
                    modifier = Modifier
                        .size(20.dp)
                )
            }

            Spacer(Modifier.size(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .weight(1f),
                color = titleColor
            )
            Spacer(Modifier.size(8.dp))

            trailing()
        }

        if (showDivider) {
            Row {
                Spacer(
                    Modifier
                        .width(52.dp)
                        .height(1.dp)
                        .background(color = MaterialTheme.colorScheme.secondary)
                )
                Divider(color = MaterialTheme.colorScheme.onSurface.copy(alpha = DividerAlpha))
            }
        }
    }
}
