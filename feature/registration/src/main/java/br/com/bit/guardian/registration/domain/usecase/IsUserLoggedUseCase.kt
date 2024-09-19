package br.com.bit.guardian.registration.domain.usecase

import kotlinx.coroutines.flow.Flow

interface IsUserLoggedUseCase {
    operator fun invoke(): Flow<Boolean>
}