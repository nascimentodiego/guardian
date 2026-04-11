package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.registration.data.datasource.remote.response.UserLoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLoginDataSource : LoginDataSource {

    private val defaultResponse = UserLoginResponse(
        uuid = "uuid-123",
        name = "Test User",
        email = "test@example.com",
        photoUrl = ""
    )

    var signInResult: Flow<UserLoginResponse> = flowOf(defaultResponse)
    var createUserResult: Flow<UserLoginResponse> = flowOf(defaultResponse)
    var signOutResult: Flow<Unit> = flowOf(Unit)
    var isUserLoggedResult: Flow<Boolean> = flowOf(false)

    override suspend fun signIn(email: String, password: String): Flow<UserLoginResponse> =
        signInResult

    override suspend fun createUser(email: String, password: String): Flow<UserLoginResponse> =
        createUserResult

    override suspend fun signOut(): Flow<Unit> = signOutResult
    override suspend fun isUserLogged(): Flow<Boolean> = isUserLoggedResult
}
