package br.com.bit.guardian.settings.data.repository.di

import br.com.bit.guardian.settings.data.repository.SettingsRepository
import br.com.bit.guardian.settings.data.repository.SettingsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsRepositoryModule {
    @Binds
    abstract fun bindsSettingsRepository(
        repository: SettingsRepositoryImpl
    ): SettingsRepository
}