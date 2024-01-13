package br.com.bit.guardian.core.network.di

import br.com.bit.guardian.core.network.retrofit.API_URL
import br.com.bit.guardian.core.network.retrofit.CONNECT_TIMEOUT
import br.com.bit.guardian.core.network.retrofit.HOTSPOT_URL
import br.com.bit.guardian.core.network.retrofit.OkHttpApiClient
import br.com.bit.guardian.core.network.retrofit.OkHttpHotSpotClient
import br.com.bit.guardian.core.network.retrofit.RetrofitAPIHotSpot
import br.com.bit.guardian.core.network.retrofit.RetrofitAPINetwork
import br.com.bit.guardian.core.network.retrofit.contentType
import br.com.bit.guardian.core.network.retrofit.providesNetworkJson
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @OkHttpApiClient
    @Provides
    fun provideApiOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .connectTimeout(CONNECT_TIMEOUT)
            .build()
    }

    @OkHttpHotSpotClient
    @Provides
    fun provideHotSpotOkHttp(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(HttpLoggingInterceptor.Level.BODY)
                }
            )
            .connectTimeout(CONNECT_TIMEOUT)
            .build()
    }

    @RetrofitAPINetwork
    @Provides
    fun provideRetrofitApiService(
        @OkHttpApiClient okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(API_URL)
            .client(okHttpClient)
            .addConverterFactory(
                providesNetworkJson.asConverterFactory(contentType)
            ).build()
    }

    @RetrofitAPIHotSpot
    @Provides
    fun provideRetrofitHotSpotApiService(
        @OkHttpHotSpotClient okHttpClient: OkHttpClient
    ): Retrofit {

        return Retrofit.Builder()
            .baseUrl(HOTSPOT_URL)
            .client(okHttpClient)
            .addConverterFactory(
                providesNetworkJson.asConverterFactory(contentType)
            ).build()
    }

}