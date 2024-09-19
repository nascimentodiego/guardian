package br.com.bit.guardian.registration.ui.login.model

sealed interface LoginIntent {
    data class InputEmail(val email: String) : LoginIntent
    data class InputPassword(val password: String) : LoginIntent
}