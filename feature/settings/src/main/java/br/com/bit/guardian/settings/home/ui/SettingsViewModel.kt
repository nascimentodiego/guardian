package br.com.bit.guardian.settings.home.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.ui.viewmodel.Event
import br.com.bit.guardian.core.ui.viewmodel.ViewModel
import br.com.bit.guardian.settings.home.domain.usecase.SaveAvatarUseCase
import br.com.bit.guardian.settings.home.domain.usecase.SaveNicknameUseCase
import br.com.bit.guardian.settings.home.domain.usecase.SettingsUseCase
import br.com.bit.guardian.settings.home.domain.usecase.SignOutUseCase
import br.com.bit.guardian.settings.home.ui.mappers.toUiState
import br.com.bit.guardian.settings.home.ui.model.SettingsEvent
import br.com.bit.guardian.settings.home.ui.model.SettingsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class SettingsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    @Named("AppVersion")
    private val appVersion: String,
    private val settingsUseCase: SettingsUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
    private val saveNicknameUseCase: SaveNicknameUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel<SettingsUiState, SettingsEvent>(savedStateHandle) {
    init {
        if (!restoreState()) {
            fetchSettings()
        }
    }

    fun fetchSettings() = viewModelScope.launch {
        settingsUseCase()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        val response = result.data.toUiState(appVersion)
                        publish(response)
                    }

                    is Result.Loading -> {
                        publish(SettingsUiState.Loading)
                    }

                    is Result.Error -> {
                        publish(SettingsUiState.Error)
                    }
                }
            }.collect()
    }

    fun saveAvatar(avatarId: Int) = viewModelScope.launch {
        saveAvatarUseCase(avatarId).asResult().map { result ->
            when (result) {
                is Result.Success -> {
                    uiState.withData {
                        val state = it as SettingsUiState.Success
                        publish(state.copy(icon = avatarId))
                    }
                }

                is Result.Loading -> { }
                is Result.Error -> { }
            }
        }.collect()
    }

    fun saveNickname(nickname: String) = viewModelScope.launch {
        saveNicknameUseCase(nickname).asResult().map { result ->
            when (result) {
                is Result.Success -> {
                    uiState.withData {
                        val state = it as SettingsUiState.Success
                        publish(state.copy(nickname = nickname))
                    }
                }

                is Result.Loading -> {}
                is Result.Error -> {}
            }
        }.collect()
    }

    fun signOut() = viewModelScope.launch {
        signOutUseCase()
            .asResult()
            .map { result ->
                when (result) {
                    is Result.Success -> {
                        sendEvent(SettingsEvent.SignOutSuccess)
                    }

                    is Result.Loading -> {}
                    is Result.Error -> {
                        sendEvent(SettingsEvent.SignOutSuccess)
                    }
                }
            }.collect()
    }
}