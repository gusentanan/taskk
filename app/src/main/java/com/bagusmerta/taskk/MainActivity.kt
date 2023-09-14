package com.bagusmerta.taskk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.core.view.WindowCompat
import com.bagusmerta.taskk.navigation.MainNavHost
import com.bagusmerta.taskk.presentation.screen.host.ui.Host
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Taskk_Light)
        super.onCreate(savedInstanceState)
        if(BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())

        setContent {
            Host {
                Surface {
                    MainNavHost()
                }
            }
        }
    }
}