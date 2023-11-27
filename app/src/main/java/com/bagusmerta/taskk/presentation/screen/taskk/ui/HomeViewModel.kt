package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.model.TaskkList
import com.bagusmerta.taskk.navigation.ARG_LIST_ID
import com.bagusmerta.taskk.presentation.screen.taskk.data.IHomeEnvironment
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    homeEnvironment: IHomeEnvironment
): StateViewModel<HomeState, HomeEffect, HomeEvent, IHomeEnvironment>(HomeState(), homeEnvironment) {

    private val listId = savedStateHandle.get<String>(ARG_LIST_ID)

    init {
        initAllState()
        initTaskkDiff()
    }

    private fun initAllState(){
        viewModelScope.launch {
            if (listId != null) {
                environment.getOverallCountTaskk()
                environment.getListTaskk(listId)
                    .catch {  }
                    .collect {
                        setState {
                            setAllState(it)
                        }
                    }
            }
        }
    }

    private fun initTaskkDiff() {
        viewModelScope.launch {
            environment.listenTaskkDiff()
                .collect()
        }
    }

    private fun HomeState.setAllState(list: TaskkList) = copy(
        taskkList = list
    )

    override fun dispatch(event: HomeEvent) {
        when(event){
            is HomeEvent.TaskkEvent -> {
                handleTaskkEvent(event)
            }
        }
    }

    private fun handleTaskkEvent(event: HomeEvent){
        when(event){
            is HomeEvent.TaskkEvent.Delete -> {
                viewModelScope.launch {
                    environment.deleteTask(event.task)
                }
            }
            is HomeEvent.TaskkEvent.OnToggleStatus -> {
                viewModelScope.launch {
                    environment.toggleTaskStatus(event.task)
                }
            }
        }
    }

}