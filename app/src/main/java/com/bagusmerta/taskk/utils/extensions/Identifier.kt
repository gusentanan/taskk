package com.bagusmerta.taskk.utils.extensions

import com.bagusmerta.taskk.presentation.screen.taskk.ui.TaskkItem

fun TaskkItem.identifier() = when(this){
    is TaskkItem.CompleteHeader -> id
    is TaskkItem.Complete -> taskk.id
    is TaskkItem.InProgress -> taskk.id
}