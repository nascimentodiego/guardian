package br.com.bit.guardian.registration.util.fakes

import br.com.bit.guardian.registration.data.repository.LoginRepository
import br.com.bit.guardian.registration.data.repository.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

open class FakeLoginRepository : LoginRepository {

    var signInResult: Flow<User> = flowOf(User(name = "Test User", email = "test@example.com", photoUrl = ""))
    var createUserResult: Flow<User> = flowOf(User(name = "Test User", email = "test@example.com", photoUrl = ""))
    var isUserLoggedResult: Flow<Boolean> = flowOf(false)
    var signOutResult: Flow<Unit> = flowOf(Unit)

    override fun signIn(email: String, password: String): Flow<User> = signInResult
    override fun createUser(email: String, password: String): Flow<User> = createUserResult
    override fun isUserLogged(): Flow<Boolean> = isUserLoggedResult
    override fun signOut(): Flow<Unit> = signOutResult
}
