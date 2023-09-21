package com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.presentation.designsystem.component.TskIcon
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalCell
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailEvent
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.presentation.screen.detail.ui.PriorityItems
import com.bagusmerta.taskk.utils.extensions.displayable

@Composable
fun DetailTaskkPriorityScreen(
    viewModel: DetailViewModel,
    onItemClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TskModalLayout {
        items(state.priorityItems){ item ->
            PriorityOptionsComponent(
                onClick = {
                    viewModel.dispatch(DetailEvent.TaskkPriorityEvent.SelectPriority(item))
                    onItemClick()
                 },
                item = item
            )
            Spacer(Modifier.height(7.dp))
        }
    }

}

@Composable
fun PriorityOptionsComponent(
    onClick: () -> Unit,
    item: PriorityItems
){
    TskModalCell(
        onClick = { onClick() },
        text = item.priority.displayable(),
        color = if(item.applied){
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondary
        },
        rightIcon = if(item.applied){
            @Composable {
                TskIcon(imageIcon = TaskkIcon.RoundedCheck)
            }
        } else {
            null
        }
    )
}
