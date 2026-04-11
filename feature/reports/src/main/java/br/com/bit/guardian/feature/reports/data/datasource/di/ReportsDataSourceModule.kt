package br.com.bit.guardian.feature.reports.data.datasource.di

import br.com.bit.guardian.feature.reports.data.datasource.ReportsDataSource
import br.com.bit.guardian.feature.reports.data.datasource.remote.firebase.ReportsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ReportsDataSourceModule {
    @Binds
    abstract fun bindsReportsDataSource(
        dataSourceImpl: ReportsDataSourceImpl
    ): ReportsDataSource
}
