package br.com.bit.guardian.core.network.retrofit

import br.com.bit.guardian.core.network.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val API_URL = BuildConfig.API_URL
private const val HOTSPOT_URL = BuildConfig.BIT_HOTSPOT_URL

class GuardianNetwork {
    private val contentType = "application/json".toMediaType()
    private val providesNetworkJson = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(OkhttpApiClient.okHttpClient)
        .addConverterFactory(
            providesNetworkJson.asConverterFactory(contentType)
        )
        .build()

}