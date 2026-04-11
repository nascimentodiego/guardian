package br.com.bit.guardian.registration.data.repository

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.datasource.local.UserPreferencesDataSource
import br.com.bit.guardian.datasource.local.model.UserStorage
import br.com.bit.guardian.registration.data.repository.mappers.toUser
import br.com.bit.guardian.registration.data.repository.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import br.com.bit.guardian.registration.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.registration.data.datasource.remote.response.UserLoginResponse
import kotlinx.coroutines.flow.first

class LoginRepositoryImpl @Inject constructor(
    private val dataSource: LoginDataSource,
    private val userDataSource: UserPreferencesDataSource
) : LoginRepository {
    override fun createUser(email: String, password: String): Flow<User> = flow {
        dataSource.createUser(email, password).collect { userResponse ->
            val user = userDataSource.updateUSerPreference(
                UserStorage(
                    userResponse.uuid.orEmpty(),
                    userResponse.name,
                    userResponse.email,
                    userResponse.photoUrl
                )
            ).first()

            emit(
                UserLoginResponse(
                    user.uuid,
                    user.name,
                    user.email,
                    user.photoUrl
                ).toUser()
            )
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun signIn(email: String, password: String): Flow<User> = flow {
        dataSource.signIn(email, password).collect { userResponse ->
            userDataSource.updateUSerPreference(
                UserStorage(
                    userResponse.uuid.orEmpty(),
                    userResponse.name,
                    userResponse.email,
                    userResponse.photoUrl
                )
            ).first()

            emit(userResponse.toUser())
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun signOut(): Flow<Unit> = flow {
        dataSource.signOut().collect {
            emit(it)
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()

    override fun isUserLogged(): Flow<Boolean> = flow {
        val result = dataSource.isUserLogged().first()
        emit(result)
    }.flowOn(Dispatchers.IO).handleNetworkError()
}
