package br.com.bit.guardian.core.common.network.exceptions

import br.com.bit.guardian.core.common.network.model.ApiError
import kotlinx.serialization.json.Json
import retrofit2.HttpException

// The server cannot or will not process the request due to something that is perceived
// to be a client error
private const val HTTP_400_BAD_REQUEST = 400

// Although the HTTP standard specifies "unauthorized",
// semantically this response means "unauthenticated".
private const val HTTP_401_UNAUTHORIZED = 401

// The client does not have access rights to the content;
private const val HTTP_403_FORBIDDEN = 403

// The server cannot find the requested resource.
private const val HTTP_404_NOT_FOUND = 404

// The server has encountered a situation it does not know how to handle.
private const val HTTP_500_SERVER_ERROR = 500

private val json = Json { isLenient = true }

sealed class GuardianApiException : Exception() {
    data object NoInternetConnectionException : GuardianApiException()
    data object ServerTimeoutException : GuardianApiException()
    data object UnauthorizedException : GuardianApiException()
    data object ForbiddenException : GuardianApiException()
    data object NotFoundException : GuardianApiException()
    data object GenericErrorException : GuardianApiException()
    data object ServerErrorException : GuardianApiException()
    data class BadRequestException(val apiError: ApiError?) : GuardianApiException() {
        constructor(errorBody: String?) : this(
            apiError = errorBody?.let {
                try {
                    json.decodeFromString<ApiError>(it)
                } catch (t: Throwable) {
                    null
                }
            }
        )
    }
}

fun HttpException.parserApiNetworkError(): GuardianApiException {
    return when (this.code()) {
        HTTP_400_BAD_REQUEST -> GuardianApiException.BadRequestException(
            this.response()?.errorBody()?.string()
        )

        HTTP_401_UNAUTHORIZED -> GuardianApiException.UnauthorizedException
        HTTP_403_FORBIDDEN -> GuardianApiException.ForbiddenException
        HTTP_404_NOT_FOUND -> GuardianApiException.NotFoundException
        HTTP_500_SERVER_ERROR -> GuardianApiException.ServerErrorException
        else -> GuardianApiException.GenericErrorException
    }
}


