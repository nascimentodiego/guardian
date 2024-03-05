package br.com.bit.guardian.login.data.datasource.remote.service

import br.com.bit.guardian.login.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.login.data.datasource.remote.request.UserLoginRequest
import br.com.bit.guardian.login.data.datasource.remote.response.UserLoginResponse
import br.com.bit.guardian.login.data.datasource.remote.service.api.LoginNetworkApi
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val api: LoginNetworkApi) : LoginDataSource {
    override suspend fun createUser(email: String, password: String): UserLoginResponse {
        return api.createUser(UserLoginRequest(email, password))
    }
}