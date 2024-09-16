package br.com.bit.guardian.registration.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.bit.guardian.core.common.result.Result
import br.com.bit.guardian.core.common.result.asResult
import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import br.com.bit.guardian.registration.domain.usecase.CreateUserUseCase
import br.com.bit.guardian.registration.domain.usecase.EmailValidationUseCase
import br.com.bit.guardian.registration.domain.usecase.PasswordValidationUseCase
import br.com.bit.guardian.registration.ui.register.mappers.toUiPasswordError
import br.com.bit.guardian.registration.ui.register.model.Event
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationRuleState.Companion.Empty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val useCase: CreateUserUseCase,
    private val emailUseCase: EmailValidationUseCase,
    private val passwordUseCase: PasswordValidationUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle(Empty))
    val uiState: StateFlow<RegisterUiState?> = _uiState.asStateFlow()

    private val _eventChannel = Channel<Event>()
    val events = _eventChannel.receiveAsFlow()

    fun createNewUser() {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val email = ruleState.email
        val password = ruleState.password

        viewModelScope.launch {
            useCase(email, password)
                .asResult()
                .map { result ->
                    _uiState.emit(
                        when (result) {
                            is Result.Success -> {
                                RegisterUiState.Success(_uiState.value.ruleState)
                            }

                            is Result.Loading -> RegisterUiState.Loading(_uiState.value.ruleState)
                            is Result.Error -> RegisterUiState.Error(_uiState.value.ruleState)
                        }
                    )
                }.collect()
        }
    }

    fun sendEvent(){
        viewModelScope.launch {
            val uuid = UUID.randomUUID().toString()
            _eventChannel.send(Event.Error(uuid))
        }
    }

    fun putEmail(email: String) {
        viewModelScope.launch {
            emailUseCase(email).map {
                handlerEmail(email, it)
            }.collect()
        }
    }

    fun putPassword(password: String) {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val confPassword = ruleState.passwordConfirm
        val email = ruleState.email

        viewModelScope.launch {
            passwordUseCase(password.trim(), confPassword, email).map { passwordErrorList ->
                handlerPassword(password, confPassword, passwordErrorList)
            }.collect()
        }
    }

    fun putConfirmPassword(confPassword: String) {
        val ruleState = (_uiState.value as RegisterUiState.Idle).ruleState
        val password = ruleState.password
        val email = ruleState.email

        viewModelScope.launch {
            passwordUseCase(password, confPassword, email).map { passwordErrorList ->
                handlerPassword(password, confPassword, passwordErrorList)
            }.collect()
        }
    }

    private fun handlerEmail(email: String, isValid: Boolean) {
        if (_uiState.value is RegisterUiState.Idle) {
            val newState = (_uiState.value as RegisterUiState.Idle).ruleState.copy(
                email = email,
                invalidEmail = !isValid
            )
            publishState(RegisterUiState.Idle(newState.mustEnableButton()))
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

        publishState(RegisterUiState.Idle(newState.mustEnableButton()))
    }

    private fun publishState(newState: RegisterUiState) {
        viewModelScope.launch {
            _uiState.emit(newState)
        }
    }

}
