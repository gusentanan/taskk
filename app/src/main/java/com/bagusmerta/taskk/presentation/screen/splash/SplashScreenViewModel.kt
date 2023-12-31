package com.bagusmerta.taskk.presentation.screen.splash

import androidx.lifecycle.viewModelScope
import com.bagusmerta.taskk.utils.vmutils.StateViewModel
import kotlinx.coroutines.launch

class SplashScreenViewModel: StateViewModel<Unit, SplashScreenEffect, SplashScreenEvent, Unit>(Unit, Unit) {

    init {
        dispatch(SplashScreenEvent.AppLaunch)
    }
    override fun dispatch(event: SplashScreenEvent) {
        when(event){
            is SplashScreenEvent.AppLaunch -> {
                viewModelScope.launch {
                    // In this block we can perform other setup process
                    // before navigating to main page (e.g fetch token credential)
                    setEffect(SplashScreenEffect.NavigateToMain)
                }
            }
        }
    }

}