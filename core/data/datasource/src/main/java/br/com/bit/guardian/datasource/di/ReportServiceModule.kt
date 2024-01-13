package br.com.bit.guardian.datasource.di

import br.com.bit.guardian.core.network.retrofit.RetrofitAPINetwork
import br.com.bit.guardian.datasource.remote.api.ReportsNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ReportServiceModule {

    @Provides
    fun provideReportService(
        @RetrofitAPINetwork retrofit: Retrofit
    ): ReportsNetworkApi {
        return retrofit.create(ReportsNetworkApi::class.java)
    }
}
