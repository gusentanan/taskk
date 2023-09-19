package com.bagusmerta.taskk.presentation.screen.detail.ui

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.LibraryBooks
import androidx.compose.material.icons.rounded.PriorityHigh
import androidx.compose.material3.Divider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.domain.model.TaskkStatus
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithBackButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskIcon
import com.bagusmerta.taskk.presentation.designsystem.component.TskItem
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.designsystem.theme.MediumRadius
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.AlphaMedium
import com.bagusmerta.taskk.utils.DividerAlpha
import com.bagusmerta.taskk.utils.extensions.formatDateTime
import com.bagusmerta.taskk.utils.extensions.getActivity
import com.bagusmerta.taskk.utils.vmutils.HandleEffect
import com.bagusmerta.taskk.utils.wrapper.DateTimeProviderImpl
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    onBackPress: () -> Unit,
    onClickTaskkTitle: () -> Unit,
    onClickDueDate: () -> Unit,
    onClickTaskkPriority: () -> Unit,
    onClickTaskkCategory: () -> Unit,
    onClickTaskkNote: () -> Unit,
    onClickTaskkStatus: () -> Unit,
    onClickTaskkDelete: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val activity  = LocalContext.current.getActivity()
    val listState = rememberLazyListState()
    Log.d("DETAILL", state.toString())

    HandleEffect(viewModel = viewModel){
        when(it){
            is DetailEffect.ScrollTo -> {
                //TODO might cut this later
            }
        }
    }

    DetailTaskkScreen(
        header = {
            HeaderWithBackButton(
                text = "Detail Taskk",
                onClickBack =
                    //TODO: pop out to previous route
                    onBackPress

            )
        },
        taskk = state.taskk,
        note = state.taskk.note,
        //TODO: Might look at this later
        dueDateTitle = state.taskk.dueDate?.formatDateTime().toString(),
        noteUpdatedAtTitle = state.taskk.noteUpdatedAt.toString(),
        onClickTaskkTitle = { onClickTaskkTitle() },
        onClickDueDate = { /*TODO*/ },
        onClickTaskkPriority = { onClickTaskkPriority() },
        onClickTaskkCategory = { onClickTaskkCategory() },
        onClickTaskkStatus = { /*TODO*/ },
        listState = listState,
        onClickTaskkNote =  { onClickTaskkNote() },
        onCheckedChangeDueDate = { /*TODO*/ }
    )

}

@Composable
fun DetailTaskkScreen(
    header: @Composable ColumnScope.() -> Unit,
    taskk: TaskkToDo,
    note: String,
    dueDateTitle: String,
    noteUpdatedAtTitle: String,
    onClickTaskkTitle: () -> Unit,
    onClickDueDate: () -> Unit,
    onCheckedChangeDueDate: (Boolean) -> Unit,
    onClickTaskkPriority: () -> Unit,
    onClickTaskkCategory: () -> Unit,
    onClickTaskkStatus: () -> Unit,
    onClickTaskkNote: () -> Unit,
    listState: LazyListState
){

    val resources = LocalContext.current.resources

    TskLayout {
        header()

        LazyColumn(
            modifier =  Modifier.fillMaxSize(),
            state = listState
        ){
            item {
                // Taskk Item that show current item status (title, priority, etc)
                TskItemWrapper(
                    item = taskk,
                    color = Color.Transparent,
                    onClick =  onClickTaskkTitle,
                    onCheckBoxClick = onClickTaskkStatus
                )
            }

            item { Spacer(Modifier.height(10.dp)) }

            item {
                //TODO: priority section
                ActionCell(
                    title = "Add Priority",
                    shape = RoundedCornerShape(size = MediumRadius),
                    iconBgColor = commonGray,
                    leftIcon = Icons.Rounded.PriorityHigh,
                    showDivider = false,
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

            item { Spacer(Modifier.height(10.dp)) }

            item {
                //TODO: category section
                ActionCell(
                    title = "Add Category",
                    shape = RoundedCornerShape(size = MediumRadius),
                    iconBgColor = commonGray,
                    leftIcon = Icons.Rounded.LibraryBooks,
                    showDivider = false,
                    onClick = onClickTaskkCategory,
                    trailing = {
                        Row {
                            Text(
                                text = taskk.taskkCategory.str,
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

            item { Spacer(Modifier.height(10.dp)) }

            item {
                //TODO: due date section
                ActionCell(
                    title = dueDateTitle,
                    shape = RoundedCornerShape(
                        topStart = MediumRadius,
                        topEnd = MediumRadius
                    ),
                    iconBgColor = Color.Red,
                    leftIcon = Icons.Rounded.Event,
                    showDivider =true,
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
                //TODO: note section
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
                                text = "Add note",
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
                                text = stringResource( R.string.taskk_add_note) + "・" + noteUpdatedAtTitle,
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaMedium)
                            )
                        }
                    }
                }
            }

        }
    }

}

@Composable
fun TskItemWrapper(
    item: TaskkToDo,
    color: Color,
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit,
){
    val coroutineScope = rememberCoroutineScope()
    when(item.status){
        TaskkStatus.COMPLETE -> {
            TskItem(
                modifier = Modifier,
                onClick = { onClick() },
                onCheckBoxClick = { onCheckBoxClick() },
                color = color.copy(alpha = AlphaDisabled),
                tskTitle = item.name,
                tskDueDate = item.dueDate?.formatDateTime().toString(),
                tskCategory = item.taskkCategory.str,
                taskkPriority = item.taskkPriority,
                contentPadding = PaddingValues(all = 8.dp),
                leftIcon = TaskkIcon.Check
            )
        }
        TaskkStatus.IN_PROGRESS -> {
            var isChecked by remember { mutableStateOf(false) }
            var debounceJob: Job? by remember { mutableStateOf(null) }

            TskItem(
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
                color = Color.Black,
                tskTitle = item.name,
                tskDueDate = item.dueDate?.formatDateTime().toString(),
                tskCategory = item.taskkCategory.str,
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
                .padding(horizontal = 16.dp)
                .clip(shape)
                .clickable(onClick = onClick),
            shape = shape,
            color = MaterialTheme.colorScheme.secondary
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
                .padding(horizontal = 16.dp),
            shape = shape,
            color = MaterialTheme.colorScheme.secondary
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
                modifier = Modifier.weight(1f),
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

fun TaskkToDo.isDueDateSet(): Boolean = this.dueDate != null
fun TaskkToDo.isExpired(currentDate: LocalDateTime = DateTimeProviderImpl().getNowDate()): Boolean {
    return dueDate?.isBefore(currentDate) ?: false
}