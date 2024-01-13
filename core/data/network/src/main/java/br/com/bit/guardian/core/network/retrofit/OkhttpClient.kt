package br.com.bit.guardian.core.network.retrofit

import java.time.Duration
import javax.inject.Qualifier

internal val CONNECT_TIMEOUT = Duration.ofSeconds(10)

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpApiClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OkHttpHotSpotClient
