package br.com.bit.guardian.registration.domain.usecase.login

import kotlinx.coroutines.flow.Flow

interface IsUserLoggedUseCase {
    operator fun invoke(): Flow<Boolean>
}