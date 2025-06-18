package br.com.bit.guardian.settings.home.domain.usecase

import br.com.bit.guardian.settings.home.data.repository.SettingsRepository
import br.com.bit.guardian.settings.home.domain.entities.SettingsEntity
import br.com.bit.guardian.settings.home.domain.mappers.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsUseCaseImpl @Inject constructor(
    private val repository: SettingsRepository
) :SettingsUseCase {
    override fun invoke(): Flow<SettingsEntity> {
        return repository.fetchSettings().map { response ->
            response.toEntity()
        }
    }
}