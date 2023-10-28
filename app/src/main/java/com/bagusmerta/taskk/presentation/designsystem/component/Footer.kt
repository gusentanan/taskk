package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun FooterWithText(
    textFooter: String
) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = textFooter,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Composable
fun FooterWithButton(
    onClick: () -> Unit,
    textButton: String
) {
    TskModalLayout(
        content = {
            item {
                TskButton(
                    onClick = { onClick() },
                    modifier = Modifier.fillMaxWidth()
                        .padding(start = 80.dp, end = 80.dp),
                ) {
                    Text(
                        text = textButton,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    )
}