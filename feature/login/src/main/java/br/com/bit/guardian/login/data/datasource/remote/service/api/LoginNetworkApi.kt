package br.com.bit.guardian.login.data.datasource.remote.service.api

import br.com.bit.guardian.login.data.datasource.remote.request.UserLoginRequest
import br.com.bit.guardian.login.data.datasource.remote.response.UserLoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginNetworkApi {
    @POST(value = "user")
    suspend fun createUser(@Body request: UserLoginRequest): UserLoginResponse
}