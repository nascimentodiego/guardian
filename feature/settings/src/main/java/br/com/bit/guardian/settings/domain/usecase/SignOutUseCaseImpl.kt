package br.com.bit.guardian.settings.domain.usecase

import br.com.bit.guardian.settings.data.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SignOutUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) : SignOutUseCase {
    override fun invoke(): Flow<Unit> {
       return repository.signOut()
    }
}