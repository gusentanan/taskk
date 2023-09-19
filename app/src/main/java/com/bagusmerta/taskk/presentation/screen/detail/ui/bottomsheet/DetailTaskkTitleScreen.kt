package com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.presentation.designsystem.component.TskInputText
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.designsystem.component.TskTextField
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailEvent
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.utils.extensions.requestFocusIme
import kotlinx.coroutines.launch

@Composable
fun DetailTaskkTitleScreen(
    viewModel: DetailViewModel,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        launch { focusRequest.requestFocusIme() }
        viewModel.dispatch(DetailEvent.TaskkTitleEvent.OnShow)
    }

    TskInputText(
        title = state.editTaskkTitle,
        positiveText = "Add Title",
        placeHolderValue = "Add Task Title",
        isValidTitle = state.validTaskkTitle,
        focusRequester = focusRequest,
        onTitleChange = { viewModel.dispatch(DetailEvent.TaskkTitleEvent.ChangeTaskkTitle(it)) },
        onSaveClick = {
            viewModel.dispatch(DetailEvent.TaskkTitleEvent.OnClickSave)
            onSaveClick()
        },
        onCancelClick = onCancelClick
    )
}