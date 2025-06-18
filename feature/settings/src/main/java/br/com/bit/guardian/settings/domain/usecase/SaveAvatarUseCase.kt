package br.com.bit.guardian.settings.domain.usecase

import kotlinx.coroutines.flow.Flow

interface SaveAvatarUseCase {
    operator fun invoke(avatarId: Int): Flow<Unit>
}