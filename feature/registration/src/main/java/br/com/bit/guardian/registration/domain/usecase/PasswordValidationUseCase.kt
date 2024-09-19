package br.com.bit.guardian.registration.domain.usecase

import br.com.bit.guardian.registration.domain.entities.PasswordError
import kotlinx.coroutines.flow.Flow

interface PasswordValidationUseCase {
    operator fun invoke(
        password: String,
        confirmPassword: String,
        email: String
    ): Flow<List<PasswordError>>
}