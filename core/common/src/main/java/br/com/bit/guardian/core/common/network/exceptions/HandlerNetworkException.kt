package br.com.bit.guardian.core.common.network.exceptions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException

fun <T> Flow<T>.handleNetworkError(): Flow<T> = catch {
    when (it) {
        is ConnectException -> throw GuardianApiException.NoInternetConnectionException
        is SocketTimeoutException -> throw GuardianApiException.ServerTimeoutException
        is HttpException -> throw it.parserApiNetworkError()
        else -> throw GuardianApiException.GenericErrorException
    }
}




