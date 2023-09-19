package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.runtime.remember
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val MAX_TEXT_FIELD_CHAR =  225


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TskTextField(
    valueText: TextFieldValue,
    onValueTextChange: (TextFieldValue) -> Unit,
    placeHolderValue: String,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textStyle: TextStyle = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface),
    errorLabel: @Composable (() -> Unit)? = null
) {
        OutlinedTextField(
            value = valueText,
            onValueChange = {
                if(it.text.length <= MAX_TEXT_FIELD_CHAR){
                    onValueTextChange(it)
                }
            },
            placeholder = {
                Text(
                    text = placeHolderValue,
                    style = textStyle,
                    color = textColor
                )
            },
            modifier = modifier
                .height(56.dp)
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = shape
                ),
            visualTransformation = visualTransformation,
            textStyle = textStyle,
            isError = isError,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                unfocusedSupportingTextColor = Color.Transparent
            )
        )
        if(errorLabel != null && isError){
            errorLabel()
        }
}

@Composable
fun TskInputText(
    title: TextFieldValue,
    positiveText: String,
    placeHolderValue: String,
    isValidTitle: Boolean,
    focusRequester: FocusRequester,
    onTitleChange: (TextFieldValue) -> Unit,
    onCancelClick: () -> Unit,
    onSaveClick: () -> Unit
){
    TskModalLayout(
        content = {
            item {
                TskTextField(
                    valueText = title,
                    onValueTextChange = { onTitleChange(it) },
                    placeHolderValue = placeHolderValue,
                    modifier = Modifier
                        .padding(16.dp)
                        .height(50.dp)
                        .fillMaxWidth()
                        .focusRequester(focusRequester),
                    shape = MaterialTheme.shapes.large
                )
            }

            item {
                Row(modifier = Modifier.padding(16.dp)) {
                    TskSecondaryButton(
                        modifier = Modifier.weight(1F),
                        onClick = onCancelClick,
                    ) {
                        Text(text = "Cancel", color = MaterialTheme.colorScheme.onSecondary)
                    }

                    Spacer(Modifier.width(16.dp))

                    TskButton(
                        modifier = Modifier.weight(1F),
                        onClick = onSaveClick,
                        enabled = isValidTitle
                    ) {
                        Text(text = positiveText, color = Color.White)
                    }
                }
            }
        }
    )

}