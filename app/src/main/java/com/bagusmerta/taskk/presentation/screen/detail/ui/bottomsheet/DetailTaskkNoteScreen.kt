package com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.presentation.designsystem.component.TskButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailEvent
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.extensions.requestFocusIme
import kotlinx.coroutines.launch

@Composable
fun DetailTaskkNoteScreen(
    viewModel: DetailViewModel,
    onClickSave: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val focusRequest = remember {
        FocusRequester()
    }
    
    LaunchedEffect(Unit){
        launch { focusRequest.requestFocusIme() }
        viewModel.dispatch(DetailEvent.TaskkNoteEvent.OnShow)
    }

    TskModalLayout {
        item {
            Column(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Box {
                  BasicTextField(
                      value = state.editTaskkNote,
                      onValueChange = {
                        viewModel.dispatch(DetailEvent.TaskkNoteEvent.ChangeTaskkNote(it))
                      },
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(150.dp)
                          .focusRequester(focusRequest),
                      textStyle = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                      keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences)
                  )

                    if(state.editTaskkNote.text.isBlank()){
                        Text(
                            text = stringResource(R.string.detail_note_placeholder_text),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
                        )
                    }
                }

                Spacer(Modifier.size(16.dp))

                TskButton(
                    modifier =Modifier.fillMaxSize(),
                    onClick = {
                        viewModel.dispatch(DetailEvent.TaskkNoteEvent.OnClickSave)
                        onClickSave()
                    }
                ) {
                    Text(text = stringResource(R.string.button_save_text), color = Color.White)
                }
                Spacer(Modifier.height(10.dp))
            }
        }
    }
    
}