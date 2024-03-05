package br.com.bit.guardian.login.domain.di

import br.com.bit.guardian.login.domain.usecase.CreateUserUseCase
import br.com.bit.guardian.login.domain.usecase.CreateUserUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDomainModule {
    @Binds
    abstract fun provideCreateUserUseCase(
        useCaseImpl: CreateUserUseCaseImpl
    ): CreateUserUseCase
}