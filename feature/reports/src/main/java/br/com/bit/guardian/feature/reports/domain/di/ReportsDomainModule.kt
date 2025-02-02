package br.com.bit.guardian.feature.reports.domain.di

import br.com.bit.guardian.feature.reports.domain.usecase.ReportsUseCase
import br.com.bit.guardian.feature.reports.domain.usecase.ReportsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReportsDomainModule {
    @Binds
    abstract fun provideReportsUserUseCase(
        useCaseImpl: ReportsUseCaseImpl
    ): ReportsUseCase
}
