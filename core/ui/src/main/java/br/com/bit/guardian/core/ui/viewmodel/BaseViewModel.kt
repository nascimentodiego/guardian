package br.com.bit.guardian.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<UiState,Event>:ViewModel() {

    protected val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState.asStateFlow()

    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    protected fun publish(newState:UiState){
        viewModelScope.launch {
            _uiState.emit(newState)
        }
    }

    protected fun sendEvent(ev:Event){
        viewModelScope.launch {
            _eventChannel.send(ev)
        }
    }

}