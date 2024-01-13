package br.com.bit.guardian.core.domain.di

import br.com.bit.guardian.core.domain.usecase.GetReportsUseCase
import br.com.bit.guardian.core.domain.usecase.GetReportsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {
    @Binds
    abstract fun provideGetReports(
        useCaseImpl: GetReportsUseCaseImpl
    ): GetReportsUseCase
}