package br.com.bit.guardian.login.data.datasource.di

import br.com.bit.guardian.core.network.retrofit.RetrofitAPINetwork
import br.com.bit.guardian.login.data.datasource.remote.service.api.LoginNetworkApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object LoginServiceModule {
    @Provides
    fun provideLoginService(
        @RetrofitAPINetwork retrofit: Retrofit
    ): LoginNetworkApi {
        return retrofit.create(LoginNetworkApi::class.java)
    }
}
