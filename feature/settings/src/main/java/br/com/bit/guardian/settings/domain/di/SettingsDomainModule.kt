package br.com.bit.guardian.settings.domain.di

import br.com.bit.guardian.settings.domain.usecase.SaveAvatarUseCase
import br.com.bit.guardian.settings.domain.usecase.SaveAvatarUseCaseImpl
import br.com.bit.guardian.settings.domain.usecase.SaveNicknameUseCase
import br.com.bit.guardian.settings.domain.usecase.SaveNicknameUseCaseImpl
import br.com.bit.guardian.settings.domain.usecase.SettingsUseCase
import br.com.bit.guardian.settings.domain.usecase.SettingsUseCaseImpl
import br.com.bit.guardian.settings.domain.usecase.SignOutUseCase
import br.com.bit.guardian.settings.domain.usecase.SignOutUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsDomainModule {
    @Binds
    abstract fun provideSettingsUseCase(
        useCaseImpl: SettingsUseCaseImpl
    ): SettingsUseCase

    @Binds
    abstract fun provideSaveAvatarUseCase(
        useCaseImpl: SaveAvatarUseCaseImpl
    ): SaveAvatarUseCase

    @Binds
    abstract fun provideSaveNicknameUseCase(
        useCaseImpl: SaveNicknameUseCaseImpl
    ): SaveNicknameUseCase

    @Binds
    abstract fun provideSignOutUseCase(
        useCaseImpl: SignOutUseCaseImpl
    ): SignOutUseCase
}