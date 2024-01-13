package br.com.bit.guardian.core.network.retrofit

import br.com.bit.guardian.core.network.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import javax.inject.Qualifier

internal const val API_URL = BuildConfig.API_URL
internal const val HOTSPOT_URL = BuildConfig.BIT_HOTSPOT_URL

internal val contentType = "application/json".toMediaType()
internal val providesNetworkJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
}


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAPINetwork

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RetrofitAPIHotSpot

class GuardianNetwork {

/*
    val retrofit = Retrofit.Builder()
        .baseUrl(API_URL)
        .client(OkhttpApiClient.okHttpClient)
        .addConverterFactory(
            providesNetworkJson.asConverterFactory(contentType)
        )
        .build()*/

}