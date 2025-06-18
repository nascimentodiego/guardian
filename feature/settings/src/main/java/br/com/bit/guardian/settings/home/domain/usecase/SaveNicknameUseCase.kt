package br.com.bit.guardian.settings.home.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SaveNicknameUseCase {
    operator fun invoke(nickname: String): Flow<Unit>
}