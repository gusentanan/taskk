package com.bagusmerta.taskk.presentation.screen.setting.ui

import androidx.core.graphics.drawable.DrawableCompat.applyTheme
import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.presentation.screen.setting.data.ISettingEnvironment
import com.bagusmerta.taskk.presentation.screen.setting.data.SettingEnvironmentImpl
import com.bagusmerta.taskk.presentation.screen.setting.ui.SettingState.Companion.initial
import com.bagusmerta.taskk.utils.themes.selectTheme
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    settingEnvironemt: SettingEnvironmentImpl
) :
    StateViewModel<SettingState, Unit, SettingEvent, ISettingEnvironment>(SettingState(), settingEnvironemt) {

    init {
        viewModelScope.launch {
            setState { copy(settingItems = initial()) }

            environment.getTheme()
                .collect {
                    setState { copy(settingItems = settingItems.selectTheme(it)) }
                }
        }


    }
    override fun dispatch(event: SettingEvent) {
        when(event){
            is SettingEvent.SelectedTheme -> applyTaskkTheme(event.selectedTheme)
        }
    }

    private fun applyTaskkTheme(item: TaskkThemeItems){
        viewModelScope.launch {
            environment.setTheme(item.type)
        }
    }


}
