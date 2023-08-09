package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

const val MAX_TEXT_FIELD_CHAR =  225

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TskTextField(
    valueText: String,
    onValueTextChange: (String) -> Unit,
    placeHolderValue: String,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
    errorLabel: @Composable (() -> Unit)? = null
) {
        OutlinedTextField(
            value = valueText,
            onValueChange = {
                if(it.length <= MAX_TEXT_FIELD_CHAR){
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

@Preview
@Composable
fun previewTskTextField(){
    TskTextField(valueText = "", onValueTextChange = {}, placeHolderValue = "Your Task Title")
}