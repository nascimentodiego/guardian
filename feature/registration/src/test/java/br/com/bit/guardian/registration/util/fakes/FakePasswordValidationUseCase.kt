package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.domain.entities.PasswordError
import br.com.bit.guardian.registration.domain.entities.PasswordErrorType
import br.com.bit.guardian.registration.domain.usecase.register.PasswordValidationUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakePasswordValidationUseCase : PasswordValidationUseCase {
    var result: List<PasswordError> = PasswordErrorType.entries.map { PasswordError(isValid = true, type = it) }

    override fun invoke(password: String, confirmPassword: String, email: String): Flow<List<PasswordError>> =
        flowOf(result)
}
