package br.com.bit.guardian.registration.ui.login

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.ui.viewmodel.ViewModel
import br.com.bit.guardian.registration.domain.usecase.login.CheckLogInInputValidUseCase
import br.com.bit.guardian.registration.domain.usecase.login.IsUserLoggedUseCase
import br.com.bit.guardian.registration.domain.usecase.login.LogInUseCase
import br.com.bit.guardian.registration.ui.login.model.LoginEvent
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.login.model.UserView.Companion.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val checkInputUseCase: CheckLogInInputValidUseCase,
    private val logInUseCase: LogInUseCase,
    private val isUseLoggedUseCase: IsUserLoggedUseCase
) : ViewModel<LoginUiState, LoginEvent>(savedStateHandle) {
    init {
        if (!restoreState()) {
            publish(LoginUiState.Loading)
        }
    }

    fun onIntent(intent: LoginIntent) = when (intent) {
        is LoginIntent.InputEmail -> putEmailOrPassword(email = intent.email)
        is LoginIntent.InputPassword -> putEmailOrPassword(password = intent.password)
        is LoginIntent.Login -> logIn()
    }

    fun isUserLogged() {
        if (hasScreenState()) return

        viewModelScope.launch {
            isUseLoggedUseCase()
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success -> {
                            if (result.data) {
                                sendEvent(LoginEvent.Success())
                            } else {
                                publishIdleState()
                            }
                        }

                        is Result.Loading -> {
                            publish(LoginUiState.Loading)
                        }

                        else -> {
                            publishIdleState()
                        }
                    }
                }.collect()
        }
    }

    private fun logIn() {
        uiState.withData { data ->
            viewModelScope.launch {
                logInUseCase(data.userView.email, data.userView.password)
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
    }

    override fun hasScreenState(): Boolean {
        return getSavedHandleState() is LoginUiState.Idle
    }

    private fun publishButtonState(isLoading: Boolean) = uiState.withData {
        publish(
            LoginUiState.Idle(it.userView.copy(isButtonLoading = isLoading))
        )
    }

    private fun publishIdleState() {
        if (!uiState.hasData()) {
            publish(LoginUiState.Idle(Empty))
        } else {
            uiState.withData {
                publish(LoginUiState.Idle(it.userView))
            }
        }
    }

    private fun putEmailOrPassword(email: String? = null, password: String? = null) {
        uiState.withData { uiState ->
            viewModelScope.launch {
                val isValid = checkInputUseCase(
                    email ?: uiState.userView.email,
                    password ?: uiState.userView.password
                ).first()

                val newState = LoginUiState.Idle(
                    uiState.userView.copy(
                        email = email ?: uiState.userView.email,
                        password = password ?: uiState.userView.password,
                        isButtonEnabled = isValid
                    )
                )
                publish(newState)
            }
        }
    }
}
