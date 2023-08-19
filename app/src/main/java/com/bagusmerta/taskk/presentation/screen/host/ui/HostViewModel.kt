package com.bagusmerta.taskk.presentation.screen.host.ui

import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.presentation.screen.host.data.IHostEnvironment
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HostViewModel @Inject constructor(hostEnvironment: IHostEnvironment):
    StateViewModel<HostState, Unit, Unit, IHostEnvironment>(HostState(), hostEnvironment) {

    init {
        initTheme()
    }

    override fun dispatch(action: Unit) {}

    private fun initTheme() {
        viewModelScope.launch {
            environment.getTheme().collect { setState { copy(themeState = it) } }
        }
    }
}