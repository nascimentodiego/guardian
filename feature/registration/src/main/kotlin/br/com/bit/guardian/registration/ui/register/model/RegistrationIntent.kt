package br.com.bit.guardian.registration.ui.register.model

sealed interface RegistrationIntent {
    data class InputEmail(val email: String) : RegistrationIntent
    data class InputPassword(val password: String) : RegistrationIntent
    data class ConfirmPassword(val password: String) : RegistrationIntent
    data object CreateUser : RegistrationIntent
}