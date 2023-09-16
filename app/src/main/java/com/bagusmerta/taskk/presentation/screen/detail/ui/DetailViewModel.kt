package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.navigation.ARG_LIST_ID
import com.bagusmerta.taskk.navigation.ARG_TASKK_ID
import com.bagusmerta.taskk.presentation.screen.detail.data.IDetailEnvironment
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    detailEnvironment: IDetailEnvironment
): StateViewModel<DetailState, DetailEffect, DetailEvent, IDetailEnvironment>(DetailState(), detailEnvironment){

    val taskkId = savedStateHandle.get<String>(ARG_TASKK_ID)
    val listId = savedStateHandle.get<String>(ARG_LIST_ID)
    init {
        viewModelScope.launch {
            if(taskkId != null && listId != null){
                environment.getTaskkById(taskkId)
                    .collect {
                        setState {
                            copy(taskk = taskk)
                        }
                    }
            }
        }
    }

    override fun dispatch(event: DetailEvent) {
        TODO("Not yet implemented")
    }

}