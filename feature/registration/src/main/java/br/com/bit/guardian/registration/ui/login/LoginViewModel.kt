package br.com.bit.guardian.registration.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.ui.viewmodel.ViewModel
import br.com.bit.guardian.registration.domain.usecase.IsUserLoggedUseCase
import br.com.bit.guardian.registration.domain.usecase.LogInUseCase
import br.com.bit.guardian.registration.ui.login.model.LoginEvent
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.login.model.UserView.Companion.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val logInUseCase: LogInUseCase,
    private val isLogInUseCase: IsUserLoggedUseCase
) : ViewModel<LoginUiState, LoginEvent>(savedStateHandle) {
    init {
        if (!restoreState()) {
            publish(LoginUiState.Loading)
        }
    }

    fun onIntent(intent: LoginIntent) = when (intent) {
        is LoginIntent.InputEmail -> putEmail(intent.email)
        is LoginIntent.InputPassword -> putPassword(intent.password)
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            logInUseCase(email, password)
                .asResult()
                .map { result ->

                    when (result) {
                        is Result.Success -> {
                            sendEvent(LoginEvent.Success())
                        }

                        is Result.Loading -> {
                            publishButtonState(true)
                        }

                        is Result.Error -> {
                            publishButtonState(false)
                            sendEvent(LoginEvent.Error())
                        }
                    }
                }.collect()
        }
    }

    fun isUserLogged() {
        viewModelScope.launch {
            isLogInUseCase()
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success -> {
                            sendEvent(LoginEvent.Success())
                        }

                        is Result.Loading -> {
                            publish(LoginUiState.Loading)
                        }

                        else -> {
                            publish(LoginUiState.Idle(Empty))
                        }
                    }
                }.collect()
        }
    }

    private fun publishButtonState(isLoading: Boolean) = publish(
        LoginUiState.Idle(
            _uiState.value!!.userView.copy(
                isLoadingButton = isLoading
            )
        )
    )


    private fun putEmail(email: String) {
        val newState = LoginUiState.Idle(
            _uiState.value!!.userView.copy(
                email = email
            )
        )
        publish(newState)
    }

    private fun putPassword(password: String) {
        val newState = LoginUiState.Idle(
            _uiState.value!!.userView.copy(
                password = password
            )
        )
        publish(newState)
    }
}
