package com.bagusmerta.taskk.presentation.screen.setting.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.presentation.designsystem.component.HeaderWithNav
import com.bagusmerta.taskk.presentation.designsystem.component.TskIcon
import com.bagusmerta.taskk.presentation.designsystem.component.TskLayout
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalCell
import com.bagusmerta.taskk.presentation.designsystem.component.TskModalLayout
import com.bagusmerta.taskk.presentation.designsystem.icon.TaskkIcon
import com.bagusmerta.taskk.utils.AlphaMedium
import com.bagusmerta.taskk.utils.extensions.AppInfo
import com.bagusmerta.taskk.utils.extensions.getAppInfo
import kotlinx.coroutines.launch
import androidx.compose.material3.Divider as MaterialDivider

@Composable
fun SettingScreen(
    viewModel: SettingViewModel,
    onClickBack: () -> Unit,
    onClickChangeTheme: () -> Unit,
    onClickInfo: () -> Unit,
){
    val state by viewModel.state.collectAsStateWithLifecycle()
    val linkHandler = LocalUriHandler.current
    val coroutineScope = rememberCoroutineScope()

    TskLayout {
        HeaderWithNav(
            text = stringResource(R.string.headline_settings),
            onClickBack = { onClickBack() }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, end = 8.dp)
                .weight(1F)
        ){
            item {
                Text(
                    text = stringResource(R.string.headline_behaviour),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )
            }

            item {
                ThemeSettingItem(onClickChangeTheme)
            }

            item {
                Box(
                    modifier = Modifier.clickable { onClickInfo() }
                ){
                    Row(
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            stringResource(R.string.subline_about_notify),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(
                            Modifier
                                .weight(1f)
                                .fillMaxHeight())
                        TskIcon(
                            imageIcon = TaskkIcon.Info,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                MaterialDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.headline_feedback),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )
            }

            item {
                val context = LocalContext.current
                val data = context.getAppInfo()
                ReportIssueItem(
                    appInformation = data,
                    onClickItem = {
                        coroutineScope.launch { linkHandler.openUri("https://github.com/gusentanan/taskk/issues") }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                MaterialDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.haedline_more_about),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
                )
            }

            item {
                AboutItem(
                    onClickItem = {
                        coroutineScope.launch { linkHandler.openUri("https://gusentanan.github.io/about") }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))
                MaterialDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start),
                    color = MaterialTheme.colorScheme.inversePrimary
                )
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }
}


@Composable
fun ThemeSettingItem(
    onClickItem: () -> Unit
){
    Box(
        modifier =  Modifier.clickable { onClickItem() }
    ){
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    stringResource(R.string.subline_theme_picker),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    stringResource(R.string.desc_theme_picker),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaMedium)
                )
            }
        }
    }
}

@Composable
fun ReportIssueItem(
    appInformation: AppInfo,
    onClickItem: () -> Unit
){
    val versionApp: String = StringBuilder("${appInformation.versionName} (${appInformation.versionCode})").toString()
    Box(
        modifier =  Modifier.clickable { onClickItem() }
    ){
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    stringResource(R.string.subline_report_issue),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    versionApp,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaMedium)
                )
            }
        }
    }

}

@Composable
fun AboutItem(
    onClickItem: () -> Unit,
){
    Box(
        modifier = Modifier.clickable { onClickItem() }
    ){
        Row(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1F)) {
                Text(
                    stringResource(R.string.subline_more_about),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    stringResource(R.string.desc_more_about),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = AlphaMedium)
                )
            }
            TskIcon(
                    imageIcon = TaskkIcon.Web,
                    modifier = Modifier.size(32.dp)
                )
        }
    }

}


@Composable
fun SettingThemeOptionScreen(
    viewModel: SettingViewModel,
    onItemClick: () -> Unit
){
    val state by viewModel.state.collectAsStateWithLifecycle()

    TskModalLayout {
        items(state.settingItems) {item ->
            SettingThemeComponent(
                onClick = {
                    viewModel.dispatch(SettingEvent.SelectedTheme(item))
                    onItemClick()
                },
                item = item
            )
            Spacer(Modifier.height(7.dp))
        }
    }
}

@Composable
fun SettingThemeComponent(
    onClick: () -> Unit,
    item: TaskkThemeItems
){
    TskModalCell(
        onClick = { onClick() },
        text = stringResource(item.title),
        textColor = Color.White,
        color = if(item.applied){
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondary
        },
        rightIcon = if(item.applied){
            @Composable {
                TskIcon(
                    imageIcon = TaskkIcon.RoundedCheck,
                    tintColor = Color.White
                )
            }
        } else {
            null
        }
    )
}
