package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.bagusmerta.taskk.presentation.designsystem.theme.gray20

@Composable
fun TskLayout(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@Composable
fun TskModalLayout(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    flag: Boolean = true,
    content: LazyListScope.() -> Unit,
) {
    TskModalLazyColumn(modifier, color) {
        if(flag){
            item {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(modifier = modifier
                        .width(80.dp)
                        .height(8.dp)
                        .background(
                            color = gray20,
                            shape = MaterialTheme.shapes.large
                        )
                    )
                }
                Spacer(Modifier.height(24.dp))
            }
        }

        item {
            Spacer(Modifier.height(10.dp))
        }

        content()

        item {
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun TskModalLazyColumn(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.background,
    shape: Shape = RectangleShape,
    content: LazyListScope.() -> Unit,
) {
    Box(
        modifier = Modifier.background(
            color = color,
            shape = shape
        )
    ) {
        LazyColumn(
            modifier = modifier
                .navigationBarsPadding()
                .imePadding(),
            content = content
        )
    }
}
