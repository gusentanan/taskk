package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon

@Composable
fun FooterWithIconBtn(
    onClickDelete: () -> Unit,
    textFooter: String
) {
    Box(
        modifier = Modifier
            .height(42.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = textFooter,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.Center)
        )

        Row(
            modifier = Modifier
                .align(Alignment.CenterEnd)
        ) {
            TskIconButton(
                onClick = onClickDelete,
                color = Color.Transparent,
                modifier = Modifier
                    .size(42.dp),
                shape = CircleShape
            ) {
                TskIcon(
                    imageIcon = TaskkIcon.Trash,
                    modifier = Modifier.size(30.dp)
                )
            }
            Spacer(modifier = Modifier.padding(end = 12.dp))
        }
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
                        color = Color.White
                    )
                }
            }
        }
    )
}