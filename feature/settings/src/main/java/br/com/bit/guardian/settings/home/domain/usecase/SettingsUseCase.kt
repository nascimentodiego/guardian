package br.com.bit.guardian.settings.home.domain.usecase

import br.com.bit.guardian.settings.home.domain.entities.SettingsEntity
import kotlinx.coroutines.flow.Flow

interface SettingsUseCase {
    operator fun invoke(): Flow<SettingsEntity>
}