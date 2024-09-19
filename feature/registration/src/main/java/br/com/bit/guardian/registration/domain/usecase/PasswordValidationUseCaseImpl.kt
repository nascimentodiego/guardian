package br.com.bit.guardian.registration.domain.usecase

import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PasswordValidationUseCaseImpl @Inject constructor() : PasswordValidationUseCase {
    override fun invoke(password: String, confirmPassword: String, email: String) = flow {
        emit(
            listOf(
                checkMinMaxCharacter(password),
                checkCapitalLetter(password),
                checkNumberCharacter(password),
                checkSpecialCharacter(password),
                checkDifferentFromEmail(password, email),
                checkMustBeEquals(password, confirmPassword)
            )
        )
    }

    private fun checkMinMaxCharacter(password: String): PasswordError {
        val isValid = password.length in 8..30

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.MIN_MAX_CHARACTER
        )
    }

    private fun checkCapitalLetter(password: String): PasswordError {
        val (upperCases, _) = password.partition { it.isUpperCase() }
        val isValid = upperCases.isNotEmpty()

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.CAPITAL_LETTER
        )
    }

    private fun checkNumberCharacter(password: String): PasswordError {
        val (digitCases, _) = password.partition { it.isDigit() }
        val isValid = digitCases.isNotEmpty()

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.NUMBER_CHARACTER
        )
    }

    private fun checkSpecialCharacter(password: String): PasswordError {
        val (_, specialCases) = password.partition { it.isLetterOrDigit() }
        val isValid = specialCases.isNotEmpty()

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.SPECIAL_CHARACTER
        )
    }

    private fun checkDifferentFromEmail(password: String, email: String): PasswordError {
        val isValid = password != email && email.isNotEmpty() && password.isNotEmpty()

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.DIFFERENT_FROM_EMAIL
        )
    }

    private fun checkMustBeEquals(password: String, confirmPassword: String): PasswordError {
        val isValid = (password == confirmPassword)
                && (password.isNotEmpty() && confirmPassword.isNotEmpty())

        return PasswordError(
            isValid = isValid,
            type = PasswordErrorType.EQUALS_PASSWORD
        )
    }
}