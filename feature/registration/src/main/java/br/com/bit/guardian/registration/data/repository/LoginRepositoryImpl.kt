package br.com.bit.guardian.registration.data.repository

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.registration.data.repository.mappers.toUser
import br.com.bit.guardian.registration.data.repository.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import br.com.bit.guardian.registration.data.datasource.remote.LoginDataSource

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource
) : LoginRepository {
    override fun createUser(email: String, password: String): Flow<User> = flow {
        dataSource.createUser(email, password).collect {
            emit(it.toUser())
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()
}
