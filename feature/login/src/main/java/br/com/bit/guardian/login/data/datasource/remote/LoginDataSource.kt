package br.com.bit.guardian.login.data.datasource.remote

import br.com.bit.guardian.login.data.datasource.remote.response.UserLoginResponse
import kotlinx.coroutines.flow.Flow

interface LoginDataSource {
    suspend fun createUser(email: String, password: String): Flow<UserLoginResponse>
}