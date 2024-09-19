package br.com.bit.guardian.core.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class ViewModel<UiState, Event>(
    protected val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val savedHandleKey = this@ViewModel::class.java.simpleName
    protected val _uiState = MutableStateFlow<UiState?>(null)
    val uiState: StateFlow<UiState?> = _uiState.asStateFlow()

    protected val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    protected fun publish(newState: UiState) {
        saveState(newState)
        _uiState.update { newState }
    }

    protected fun sendEvent(ev: Event) {
        viewModelScope.launch {
            _eventChannel.send(ev)
        }
    }

    protected fun saveState(uiState: UiState) {
        savedStateHandle[savedHandleKey] = uiState
    }

    protected fun restoreState(): Boolean {
        if (savedStateHandle.contains(savedHandleKey)) {
            savedStateHandle.get<UiState>(savedHandleKey)?.let { publish(it) }
        }
        return savedStateHandle.contains(savedHandleKey)
    }

    protected fun getSavedHandleState() = savedStateHandle.get<UiState>(savedHandleKey)

}