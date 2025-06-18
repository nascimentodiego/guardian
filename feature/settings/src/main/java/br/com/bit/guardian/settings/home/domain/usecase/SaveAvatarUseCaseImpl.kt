package br.com.bit.guardian.settings.home.domain.usecase

import br.com.bit.guardian.settings.home.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveAvatarUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) :SaveAvatarUseCase {
    override fun invoke(avatarId: Int): Flow<Unit> = repository.saveAvatar(avatarId)
}