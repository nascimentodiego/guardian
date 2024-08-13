package br.com.bit.guardian.registration.domain.usecase

import kotlinx.coroutines.flow.Flow

interface EmailValidationUseCase {
    operator fun invoke(email: String): Flow<Boolean>
}