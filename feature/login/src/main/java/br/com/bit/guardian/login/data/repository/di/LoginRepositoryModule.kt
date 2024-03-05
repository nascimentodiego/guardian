package br.com.bit.guardian.login.data.repository.di

import br.com.bit.guardian.login.data.repository.LoginRepository
import br.com.bit.guardian.login.data.repository.LoginRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginRepositoryModule {
    @Binds
    abstract fun provideLoginRepository(
        repository: LoginRepositoryImpl
    ): LoginRepository
}