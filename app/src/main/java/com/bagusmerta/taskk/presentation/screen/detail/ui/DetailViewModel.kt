package com.bagusmerta.taskk.presentation.screen.detail.ui

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.domain.model.TaskkToDo
import com.bagusmerta.taskk.navigation.ARG_LIST_ID
import com.bagusmerta.taskk.navigation.ARG_TASKK_ID
import com.bagusmerta.taskk.presentation.screen.detail.data.IDetailEnvironment
import com.bagusmerta.taskk.utils.extensions.select
import com.bagusmerta.taskk.utils.extensions.updatedDateToLocalDateTime
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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
            if(taskkId.isNullOrBlank()){
                setEffect(DetailEffect.ShowCreateTaskkNameInput)
            } else {
                environment.getTaskkById(taskkId)
                    .catch {
                        // Handle NPE on Delete taskk
                        if(it is NullPointerException){
                            setEffect(DetailEffect.OnClosePage)
                        }
                    }
                    .collect {
                        setState { setAllState(it) }
                    }
            }
        }
    }

    private fun DetailState.setAllState(data: TaskkToDo) = copy(
        taskk = data,
        priorityItems = priorityItems.select(data.taskkPriority),
        categoryItems = categoryItems.select(data.taskkCategory),
    )

    override fun dispatch(event: DetailEvent) {
       when(event){
           is DetailEvent.TaskkNoteEvent -> handleTaskkNoteEvent(event)
           is DetailEvent.TaskkPriorityEvent -> { handleTaskkPriorityEvent(event) }
           is DetailEvent.TaskkCategoryEvent -> { handleTaskkCategoryEvent(event) }
           is DetailEvent.TaskkTitleEvent -> { handleTaskkTitleEvent(event) }
           is DetailEvent.OnToggleStatus -> {
               viewModelScope.launch {
                   environment.toggleTaskStatus(event.taskk)
               }
           }
           is DetailEvent.Delete -> {
                viewModelScope.launch {
                    environment.deleteTaskk(event.taskk)
                }
           }
           is DetailEvent.ResetDueDate -> {
                viewModelScope.launch {
                    environment.resetTaskkDueDate(state.value.taskk.id)
                }
           }
           is DetailEvent.SelectDueDate -> {
                viewModelScope.launch {
                    val newDate = state.value.taskk.updatedDateToLocalDateTime(event.date)
                    environment.updateTaskkDueDate(state.value.taskk.id, newDate)
                }
           }
       }
    }

    private fun handleTaskkCategoryEvent(event: DetailEvent.TaskkCategoryEvent) {
        when(event){
            is DetailEvent.TaskkCategoryEvent.SelectCategory -> {
                viewModelScope.launch {
                    environment.updateTaskkCategory(state.value.taskk.id, event.category.category)
                }
            }
            is DetailEvent.TaskkCategoryEvent.OnShow -> {
                viewModelScope.launch {
                    setState { copy(categoryItems = categoryItems) }
                }
            }
        }
    }

    private fun handleTaskkPriorityEvent(event: DetailEvent.TaskkPriorityEvent) {
        when(event){
            is DetailEvent.TaskkPriorityEvent.OnShow -> {
                viewModelScope.launch {
                    setState { copy(priorityItems = priorityItems) }
                }
            }
            is DetailEvent.TaskkPriorityEvent.SelectPriority -> {
                viewModelScope.launch {
                    environment.updateTaskkPriority(state.value.taskk.id , event.priority.priority)
                }
            }
        }
    }

    private fun handleTaskkTitleEvent(event: DetailEvent.TaskkTitleEvent) {
        when(event){
            is DetailEvent.TaskkTitleEvent.OnClickSaveCreate -> {
                viewModelScope.launch {
                    if(state.value.validTaskkTitle){
                        environment.insertTaskkTitle(
                            environment.idProvider.generateId(),
                            state.value.editTaskkTitle.text.trim()
                        )
                            .collect{
                                setEffect(DetailEffect.RefreshScreen(it.id))
                            }
                        setState { copy(editTaskkTitle = TextFieldValue()) }
                    }

                }
            }
            is DetailEvent.TaskkTitleEvent.OnClickSaveUpdate -> {
                viewModelScope.launch {
                    if(state.value.validTaskkTitle){
                        environment.updateTaskkTitle(state.value.taskk.id, state.value.editTaskkTitle.text.trim())
                        setState { copy(editTaskkTitle = TextFieldValue()) }
                    }
                }
            }
            is DetailEvent.TaskkTitleEvent.ChangeTaskkTitle -> {
                viewModelScope.launch {
                    setState { copy(editTaskkTitle = event.title) }
                }
            }
            is DetailEvent.TaskkTitleEvent.OnShow -> {
                viewModelScope.launch {
                    setState { copy(editTaskkTitle = editTaskkTitle.copy(text = taskk.name,selection = TextRange(editTaskkTitle.text.length))) }
                }
            }
        }
    }

    private fun handleTaskkNoteEvent(event: DetailEvent.TaskkNoteEvent) {
        when(event){
            is DetailEvent.TaskkNoteEvent.OnShow -> {
                viewModelScope.launch {
                    val note = state.value.taskk.note
                    setState { copy(editTaskkNote = editTaskkNote.copy(text = note, selection = TextRange(editTaskkNote.text.length))) }
                }
            }
            is DetailEvent.TaskkNoteEvent.ChangeTaskkNote -> {
                viewModelScope.launch {
                    setState { copy(editTaskkNote = event.note) }
                }
            }
            is DetailEvent.TaskkNoteEvent.OnClickSave -> {
                viewModelScope.launch {
                    environment.updateTaskkNote(state.value.taskk.id, state.value.editTaskkNote.text)
                }
            }
        }
    }

}