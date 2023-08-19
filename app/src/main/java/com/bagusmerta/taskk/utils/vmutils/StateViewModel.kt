package com.bagusmerta.taskk.utils.vmutils

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

/**
State : refers to a data type that defines the information required for your feature to execute
        its logic and display its user interface. This data is retained throughout the lifespan of the feature.

Effect : akin to state, but it is impermanent and pertains to actions like navigation, displaying
         toasts, or showing snack-bars.

Event : pertains to a data type encompassing all the triggers that lead to changes in the application's
        state, including user interactions, notifications, and various event sources.

Environment (env):   designates a data type responsible for encapsulating any dependencies essential to the feature,
               such as API clients, analytics clients, and others. This separation serves to decouple the UI layer
               from the Data layer.
 */

abstract class StateViewModel<STATE, EFFECT, EVENT, ENV>(
    initialState: STATE,
    protected val environment: ENV
): ViewModel() {
    private val _state = MutableStateFlow(initialState)

    private val _effect: MutableStateFlow<EFFECT?> = MutableStateFlow(null)

    val state: StateFlow<STATE> = _state.asStateFlow()

    val effect: StateFlow<EFFECT?> = _effect.asStateFlow()

    abstract fun dispatch(event: EVENT)

    protected fun setState(newState: STATE.() -> STATE) {
        _state.update(newState)
    }

    protected fun setEffect(newEffect: EFFECT) {
        _effect.update { newEffect }
    }

    fun resetEffect() {
        _effect.update { null }
    }

    private fun stateValue(): STATE {
        return state.value
    }
}