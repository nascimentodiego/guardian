package br.com.bit.guardian.feature.reports.data.repository.di

import br.com.bit.guardian.feature.reports.data.repository.ReportsRepository
import br.com.bit.guardian.feature.reports.data.repository.ReportsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReportsRepositoryModule {
    @Binds
    abstract fun provideReportsRepository(
        repository: ReportsRepositoryImpl
    ): ReportsRepository
}