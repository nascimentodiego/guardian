package br.com.bit.guardian.core.network.retrofit

import br.com.bit.guardian.core.network.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.time.Duration


private const val API_IRL = BuildConfig.API_URL
private val CONNECT_TIMEOUT = Duration.ofSeconds(10)

object OkhttpApiClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
        )
        .connectTimeout(CONNECT_TIMEOUT)
        .build()
}