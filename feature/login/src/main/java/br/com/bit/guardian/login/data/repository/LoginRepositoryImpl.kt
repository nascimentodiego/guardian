package br.com.bit.guardian.login.data.repository

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.login.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.login.data.repository.mappers.toUser
import br.com.bit.guardian.login.data.repository.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
):LoginRepository {
    override fun createUser(email: String, password: String): Flow<User> = flow{
        val response = dataSource.createUser(email,password)
        emit(response.toUser())
    }.flowOn(Dispatchers.IO).handleNetworkError()
}
