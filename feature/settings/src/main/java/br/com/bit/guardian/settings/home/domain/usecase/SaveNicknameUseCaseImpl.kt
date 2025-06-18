package br.com.bit.guardian.settings.home.domain.usecase

import br.com.bit.guardian.settings.home.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveNicknameUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) : SaveNicknameUseCase {
    override fun invoke(nickname: String): Flow<Unit> {
        return repository.saveNickName(nickname)
    }
}