package com.bagusmerta.taskk.presentation.screen.taskk.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.data.model.TaskkList
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
        viewModelScope.launch {
//            environment.idProvider.generateId()

            if(listId.isNullOrBlank()){
                setEffect(HomeEffect.ShowNewTaskInput)
            } else {
                homeEnvironment.getListTaskk(listId)
                    .catch {  }
                    .collect {
                        setState {
                            setAllState(it)
                        }
                    }
            }
        }
    }

    private fun HomeState.setAllState(list: TaskkList) = copy(
        taskkList = list
    )

    override fun dispatch(event: HomeEvent) {
        TODO("Not yet implemented")
    }

}