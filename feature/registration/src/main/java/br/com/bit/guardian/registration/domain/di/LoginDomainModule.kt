package br.com.bit.guardian.registration.domain.di

import br.com.bit.guardian.registration.domain.usecase.login.CheckLogInInputValidUseCase
import br.com.bit.guardian.registration.domain.usecase.login.CheckLogInInputValidUseCaseImpl
import br.com.bit.guardian.registration.domain.usecase.register.CreateUserUseCase
import br.com.bit.guardian.registration.domain.usecase.register.CreateUserUseCaseImpl
import br.com.bit.guardian.registration.domain.usecase.register.EmailValidationUseCase
import br.com.bit.guardian.registration.domain.usecase.register.EmailValidationUseCaseImpl
import br.com.bit.guardian.registration.domain.usecase.login.IsUserLoggedUseCase
import br.com.bit.guardian.registration.domain.usecase.login.IsUserLoggedUseCaseImpl
import br.com.bit.guardian.registration.domain.usecase.login.LogInUseCase
import br.com.bit.guardian.registration.domain.usecase.login.LogInUseCaseImpl
import br.com.bit.guardian.registration.domain.usecase.register.PasswordValidationUseCase
import br.com.bit.guardian.registration.domain.usecase.register.PasswordValidationUseCaseImpl
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

    @Binds
    abstract fun provideLogInUseCase(
        useCaseImpl: LogInUseCaseImpl
    ): LogInUseCase

    @Binds
    abstract fun provideIsUserLoggedUseCase(
        useCaseImpl: IsUserLoggedUseCaseImpl
    ): IsUserLoggedUseCase

    @Binds
    abstract fun provideEmailValidationUseCase(
        useCaseImpl: EmailValidationUseCaseImpl
    ): EmailValidationUseCase

    @Binds
    abstract fun providePasswordValidationUseCase(
        useCaseImpl: PasswordValidationUseCaseImpl
    ): PasswordValidationUseCase

    @Binds
    abstract fun provideCheckInputLoginValidUseCase(
        useCaseImpl: CheckLogInInputValidUseCaseImpl
    ): CheckLogInInputValidUseCase
}