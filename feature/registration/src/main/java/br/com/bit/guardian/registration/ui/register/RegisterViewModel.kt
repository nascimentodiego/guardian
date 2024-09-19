package br.com.bit.guardian.registration.ui.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.core.ui.viewmodel.ViewModel
import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import br.com.bit.guardian.registration.domain.usecase.CreateUserUseCase
import br.com.bit.guardian.registration.domain.usecase.EmailValidationUseCase
import br.com.bit.guardian.registration.domain.usecase.PasswordValidationUseCase
import br.com.bit.guardian.registration.ui.register.mappers.toUiPasswordError
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationEvent
import br.com.bit.guardian.registration.ui.register.model.RegistrationIntent
import br.com.bit.guardian.registration.ui.register.model.RegistrationRuleState.Companion.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCase: CreateUserUseCase,
    private val emailUseCase: EmailValidationUseCase,
    private val passwordUseCase: PasswordValidationUseCase
) : ViewModel<RegisterUiState, RegistrationEvent>(savedStateHandle) {
    init {
        if (!restoreState()) {
            publish(RegisterUiState.Idle(Empty))
        }
    }

    fun onIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.InputEmail -> {
                putEmail(intent.email)
            }

            is RegistrationIntent.InputPassword -> {
                putPassword(intent.password)
            }

            is RegistrationIntent.ConfirmPassword -> {
                putConfirmPassword(intent.password)
            }

            is RegistrationIntent.CreateUser -> {
                createNewUser()
            }
        }
    }

    private fun createNewUser() {
        val ruleState = _uiState.value!!.ruleState
        val email = ruleState.email
        val password = ruleState.password

        viewModelScope.launch {
            useCase(email, password)
                .asResult()
                .map { result ->
                    when (result) {
                        is Result.Success -> sendSuccessEvent()
                        is Result.Loading -> publish(RegisterUiState.Loading(ruleState))
                        is Result.Error -> {
                            publish(RegisterUiState.Idle(ruleState))
                            sendEvent(RegistrationEvent.Error())
                        }
                    }
                }.collect()
        }
    }

    private fun putEmail(email: String) {
        viewModelScope.launch {
            emailUseCase(email).map {
                handlerEmail(email, it)
            }.collect()
        }
    }

    private fun putPassword(password: String) {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val confPassword = ruleState.passwordConfirm
        val email = ruleState.email

        viewModelScope.launch {
            passwordUseCase(password.trim(), confPassword, email).map { passwordErrorList ->
                handlerPassword(password, confPassword, passwordErrorList)
            }.collect()
        }
    }

    private fun putConfirmPassword(confPassword: String) {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val password = ruleState.password
        val email = ruleState.email

        viewModelScope.launch {
            passwordUseCase(password, confPassword, email).map { passwordErrorList ->
                handlerPassword(password, confPassword, passwordErrorList)
            }.collect()
        }
    }

    private fun sendSuccessEvent() {
        sendEvent(RegistrationEvent.Finish())
    }

    private fun handlerEmail(email: String, isValid: Boolean) {
        if (_uiState.value is RegisterUiState.Idle) {
            val newState = (_uiState.value as RegisterUiState.Idle).ruleState.copy(
                email = email,
                invalidEmail = !isValid
            )
            val uiState = RegisterUiState.Idle(newState.mustEnableButton())
            saveState(uiState)
            publish(uiState)
        }
    }

    private fun handlerPassword(
        password: String,
        confirmPassword: String,
        passwordErrorList: List<PasswordError>
    ) {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val newState = ruleState.copy(
            password = password,
            passwordConfirm = confirmPassword,
            invalidPass = passwordErrorList.firstOrNull {
                !it.isValid && it.type != PasswordErrorType.EQUALS_PASSWORD
            }?.let { true } ?: run { false },
            invalidConfPass = passwordErrorList.firstOrNull {
                it.type == PasswordErrorType.EQUALS_PASSWORD
            }?.isValid?.not() ?: false,
            listOfPassError = passwordErrorList.map { it.toUiPasswordError() }
        )
        val uiState = RegisterUiState.Idle(newState.mustEnableButton())
        saveState(uiState)
        publish(uiState)
    }
}
