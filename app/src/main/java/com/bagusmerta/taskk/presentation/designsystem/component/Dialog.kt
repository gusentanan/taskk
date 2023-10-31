package com.bagusmerta.taskk.presentation.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.presentation.designsystem.theme.MediumRadius
import com.bagusmerta.taskk.presentation.designsystem.theme.commonGray

@Composable
fun TaskkDialog(headerValue: String, description: String, setShowDialog: (Boolean) -> Unit) {

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(MediumRadius),
            color = MaterialTheme.colorScheme.background
        ) {
            Box(
                modifier = Modifier.padding(10.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Icon(
                    imageVector = Icons.Filled.Cancel,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                        .clickable { setShowDialog(false) }
                )

                Spacer(modifier = Modifier.height(40.dp))

                Column(modifier = Modifier.padding(20.dp)) {

                    Spacer(modifier = Modifier.height(20.dp))

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        // For now, the image source for this dialog is static
                        Image(
                            painter = painterResource(R.drawable.ic_avatar),
                            contentDescription = "Dialog Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(100.dp))

                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = headerValue,
                            style = MaterialTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center)
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center),
                            color = commonGray
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun previewDialog(){
    TaskkDialog(headerValue = "Apps Permission", description =
            "To ensure the Taskk app functions correctly, please grant notification permissions.", setShowDialog = { } )
}