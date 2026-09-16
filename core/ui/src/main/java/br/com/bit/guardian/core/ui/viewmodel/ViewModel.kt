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
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

abstract class ViewModel<UiState : Any, Event>(
    protected val savedStateHandle: SavedStateHandle,
    private val stateSerializer: KSerializer<UiState>
) : ViewModel() {
    private val savedHandleKey = this@ViewModel::class.java.simpleName
    private val _uiState = MutableStateFlow<UiState?>(null)
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
        savedStateHandle[savedHandleKey] = Json.encodeToString(stateSerializer, uiState)
    }

    protected fun restoreState(): Boolean {
        if (hasScreenState()) {
            getSavedHandleState()?.let { publish(it) }
        }
        return hasScreenState()
    }

    protected open fun hasScreenState() = savedStateHandle.contains(savedHandleKey)

    protected fun getSavedHandleState(): UiState? =
        savedStateHandle.get<String>(savedHandleKey)?.let { Json.decodeFromString(stateSerializer, it) }

    protected fun StateFlow<UiState?>.withData(func: (UiState) -> Unit) {
        if (hasData()) {
            func(value!!)
        }
    }

    protected fun StateFlow<UiState?>.hasData(): Boolean = value != null
}
