package com.bagusmerta.taskk.presentation.designsystem.theme

import android.app.Activity
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.bagusmerta.taskk.R
import com.bagusmerta.taskk.utils.extensions.getActivity
import com.bagusmerta.taskk.utils.themes.TaskkTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import timber.log.Timber

/**
 *  Dark Theme
 * */
private val DarkColorScheme = darkColorScheme(
    primary = dPrimary,
    primaryContainer = dPrimary,
    secondary = dItemBackground,
    secondaryContainer = dItemBackground,
    background = dBackground,
    onBackground = dOn,
    onPrimary = Color.Black,
    onPrimaryContainer = Color.Black,
    onSecondary = dOn,
    onSecondaryContainer = dOn,
    surface = dBackground,
    onSurface = dOn,
    error = dError,
    onError = Color.White,

)

/**
 *  Light Theme
 * */
private val LightColorScheme = lightColorScheme(
    primary = lPrimary,
    primaryContainer = lPrimary,
    secondary = lItemBackground,
    secondaryContainer = lItemBackground,
    background = lBackground,
    onBackground = lOn,
    onPrimary = Color.White,
    onPrimaryContainer = Color.White,
    onSecondary = lOn,
    onSecondaryContainer = lOn,
    surface = lBackground,
    onSurface = lOn,
    error = lError,
    onError = Color.White

)

/**
 *  App Theme adjustment
 * */
@Composable
fun TaskkTheme(
    theme: TaskkTheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (theme) {
        TaskkTheme.SYSTEM -> {
            if (isSystemInDarkTheme()) {
                DarkColorScheme
            } else {
                LightColorScheme
            }
        }

        TaskkTheme.DARK -> {
            DarkColorScheme
        }

        TaskkTheme.LIGHT -> {
            LightColorScheme
        }

    }

    val darkIcons = colorScheme == LightColorScheme
    val systemUiController = rememberSystemUiController()

    val activity = LocalContext.current as AppCompatActivity
    Timber.tag("THEMEE").d(activity.toString())


    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = darkIcons,
            isNavigationBarContrastEnforced = false
        )
    }

    LaunchedEffect(colorScheme) {
        when (colorScheme) {
            DarkColorScheme -> activity.setTheme(R.style.Theme_Taskk_Dark)
            LightColorScheme -> activity.setTheme(R.style.Theme_Taskk_Light)
        }
    }

    MaterialTheme(
        shapes = Shapes,
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}