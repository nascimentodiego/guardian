package br.com.bit.guardian.registration.domain.usecase.register

import kotlinx.coroutines.flow.Flow

interface EmailValidationUseCase {
    operator fun invoke(email: String): Flow<Boolean>
}