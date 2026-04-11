package br.com.bit.guardian.settings.domain.usecase

import br.com.bit.guardian.core.common.network.exceptions.GuardianApiException
import br.com.bit.guardian.core.common.network.exceptions.GuardianErrorType
import br.com.bit.guardian.settings.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveNicknameUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) : SaveNicknameUseCase {
    override fun invoke(nickname: String): Flow<Unit> {
        if (nickname.length < 3) {
            throw GuardianApiException.GuardianBusinessException(
                NicknameTooShortException
            )
        }

        return repository.saveNickName(nickname)
    }
}

data object NicknameTooShortException : Exception(), GuardianErrorType {
    @Suppress("UnusedPrivateMember")
    private fun readResolve(): Any = NicknameTooShortException
    override val message: String
        get() = "Nickname must be at least 3 characters"
}