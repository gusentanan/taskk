package com.bagusmerta.taskk.presentation.designsystem.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

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
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}