package com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.presentation.designsystem.component.TskInputText
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
        positiveText = stringResource(R.string.detail_save_button_on_title),
        placeHolderValue = stringResource(R.string.detail_taskk_add_title_text),
        isValidTitle = state.validTaskkTitle,
        focusRequester = focusRequest,
        onTitleChange = { viewModel.dispatch(DetailEvent.TaskkTitleEvent.ChangeTaskkTitle(it)) },
        onSaveClick = {
            if(state.taskk.name.isNotEmpty()){
                viewModel.dispatch(DetailEvent.TaskkTitleEvent.OnClickSaveUpdate)
            } else {
                viewModel.dispatch(DetailEvent.TaskkTitleEvent.OnClickSaveCreate)
            }
            onSaveClick()
        },
        onCancelClick = onCancelClick
    )
}