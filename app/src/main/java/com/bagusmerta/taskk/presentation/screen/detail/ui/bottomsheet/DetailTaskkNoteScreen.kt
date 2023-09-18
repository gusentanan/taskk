package com.bagusmerta.taskk.presentation.screen.detail.ui.bottomsheet

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.presentation.designsystem.component.TskButton
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailEvent
import com.bagusmerta.taskk.presentation.screen.detail.ui.DetailViewModel
import com.bagusmerta.taskk.utils.AlphaDisabled
import com.bagusmerta.taskk.utils.extensions.requestFocusIme
import kotlinx.coroutines.launch

@Composable
fun DetailTaskkNoteScreen(
    viewModel: DetailViewModel,
    onClickBack: () -> Unit
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
                        //TODO: dispatcher to viewmodel to emit changed value
                      },
                      modifier = Modifier
                          .fillMaxWidth()
                          .height(150.dp)
                          .focusRequester(focusRequest),
                      textStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                      cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                      keyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences)
                  )

                    if(state.editTaskkNote.text.isBlank()){
                        Text(
                            text = "Add note",
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaDisabled)
                        )
                    }
                }

                Spacer(Modifier.size(16.dp))

                TskButton(onClick = onClickBack) {
                    Text(text = "Save", color = Color.White)
                }

            }
        }
    }
    
}