package com.bagusmerta.taskk.utils.extensions

import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.appcompat.app.AppCompatActivity


fun Context.getActivity(): AppCompatActivity? = when (this) {
    is AppCompatActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}

fun Context.getAppInfo(): AppInfo {
    val packageManager = this.packageManager
    val packageInfo = packageManager.getPackageInfo(this.packageName, 0)
    val versionCode =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            (packageInfo.longVersionCode and 0xffffffffL).toInt()
        } else {
            @Suppress("DEPRECATION") packageInfo.versionCode
        }

    return AppInfo(
        versionName = packageInfo.versionName,
        versionCode = versionCode,
    )
}

data class AppInfo(
    val versionCode: Int,
    val versionName: String
)