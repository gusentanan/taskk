package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.TextRange
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.domain.model.TaskkToDo
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

    private val taskkId = savedStateHandle.get<String>(ARG_TASKK_ID)
    private val listId = savedStateHandle.get<String>(ARG_LIST_ID)
    init {
        initTask()
    }

    private fun initTask(){
        viewModelScope.launch {
            if(taskkId != null && listId != null){
                environment.getTaskkById(taskkId)
                    .collect {
                        setState {
                            setAllState(it)
                        }
                    }
            }
        }
    }

    private fun DetailState.setAllState(data: TaskkToDo) = copy(
        taskk = data
    )

    override fun dispatch(event: DetailEvent) {
       when(event){
           is DetailEvent.TaskkNoteEvent -> handleTaskkNoteEvent(event)
           is DetailEvent.TaskkPriorityEvent -> { }
           is DetailEvent.TaskkCategoryEvent -> { }
           is DetailEvent.TaskkTitleEvent -> { }

           else -> {}
       }
    }

    private fun handleTaskkNoteEvent(event: DetailEvent.TaskkNoteEvent) {
        when(event){
            is DetailEvent.TaskkNoteEvent.OnShow -> {
                viewModelScope.launch {
                    val note = state.value.taskk.note
                    setState { copy(editTaskkNote = editTaskkNote.copy(text = note, selection = TextRange(note.length))) }
                }
            }
            is DetailEvent.TaskkNoteEvent.ChangeTaskkNote -> { }
            is DetailEvent.TaskkNoteEvent.OnClickSave -> { }

        }
    }

}