package br.com.bit.guardian.registration.domain.entities

data class PasswordError(
    val isValid: Boolean = false,
    val type: PasswordErrorType
)
