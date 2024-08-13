package br.com.bit.guardian.registration.domain.di

import br.com.bit.guardian.registration.domain.usecase.CreateUserUseCase
import br.com.bit.guardian.registration.domain.usecase.CreateUserUseCaseImpl
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