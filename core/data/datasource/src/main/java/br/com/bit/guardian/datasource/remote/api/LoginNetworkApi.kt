package br.com.bit.guardian.datasource.remote.api

import br.com.bit.guardian.datasource.remote.request.login.UserLoginRequest
import br.com.bit.guardian.datasource.remote.response.login.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginNetworkApi {
    @POST(value = "login")
    suspend fun getReports(
        @Body request: UserLoginRequest
    ): UserLoginResponse
}