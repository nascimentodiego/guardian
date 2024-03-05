package br.com.bit.guardian.login.data.datasource.di

import br.com.bit.guardian.login.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.login.data.datasource.remote.service.LoginDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDataSourceModule {
    @Binds
    abstract fun bindsLoginDataSource(
        dataSourceImpl: LoginDataSourceImpl
    ): LoginDataSource
}

