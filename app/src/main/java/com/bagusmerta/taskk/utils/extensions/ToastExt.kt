package com.bagusmerta.taskk.utils.extensions

import android.content.Context
import android.widget.Toast

fun Context.showToast(msg: Int){
    Toast.makeText(this, getString(msg), Toast.LENGTH_LONG).show()
}