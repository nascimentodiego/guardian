package br.com.bit.guardian.registration.domain.usecase.login

import kotlinx.coroutines.flow.Flow

interface CheckLogInInputValidUseCase {
    operator fun invoke(email: String, password: String): Flow<Boolean>
}