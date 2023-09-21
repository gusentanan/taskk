package com.bagusmerta.taskk.presentation.screen.setting.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.presentation.designsystem.component.TskIcon
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalCell
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    onClickBack: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    TskModalLayout {
        items(state.settingItems) {item ->
            SettingThemeComponent(
                onClick = {
                    viewModel.dispatch(SettingEvent.SelectedTheme(item))
                    onClickBack()
                },
                item = item
            )
            Spacer(Modifier.height(7.dp))
        }
    }
}

@Composable
fun SettingThemeComponent(
    onClick: () -> Unit,
    item: TaskkThemeItems
){
    TskModalCell(
        onClick = { onClick() },
        text = item.title,
        color = if(item.applied){
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondary
        },
        rightIcon = if(item.applied){
            @Composable {
                TskIcon(imageIcon = TaskkIcon.Check)
            }
        } else {
            null
        }
    )
}
