package br.com.bit.guardian.settings.home.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SignOutUseCase {
     operator fun invoke(): Flow<Unit>
}