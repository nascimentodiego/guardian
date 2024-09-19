package br.com.bit.guardian.registration.domain.entities

enum class PasswordErrorType {
    DIFFERENT_FROM_EMAIL,
    MIN_MAX_CHARACTER,
    CAPITAL_LETTER,
    NUMBER_CHARACTER,
    SPECIAL_CHARACTER,
    EQUALS_PASSWORD
}