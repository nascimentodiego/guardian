package br.com.bit.guardian.registration.data.datasource.remote.firebase

import br.com.bit.guardian.core.common.network.exceptions.GuardianApiException
import br.com.bit.guardian.registration.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.registration.data.datasource.remote.response.UserLoginResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) : LoginDataSource {
    override suspend fun createUser(email: String, password: String): Flow<UserLoginResponse> =
        callbackFlow {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(
                            UserLoginResponse(
                                uuid = task.result.user?.uid.orEmpty(),
                                name = task.result.user?.displayName.orEmpty(),
                                email = task.result.user?.email.orEmpty(),
                                photoUrl = task.result.user?.phoneNumber.orEmpty()
                            )
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    close(exception)
                }
            awaitClose()
        }.catch { throw GuardianApiException.GenericErrorException }

    override suspend fun signIn(email: String, password: String): Flow<UserLoginResponse> =
        callbackFlow {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(
                            UserLoginResponse(
                                uuid = task.result.user?.uid.orEmpty(),
                                name = task.result.user?.displayName.orEmpty(),
                                email = task.result.user?.email.orEmpty(),
                                photoUrl = task.result.user?.photoUrl.toString()
                            )
                        )
                        task.result?.user?.uid
                    } else {
                        close(GuardianApiException.GenericErrorException)
                    }
                }

            awaitClose()
        }.catch { throw GuardianApiException.GenericErrorException }

    override suspend fun signOut(): Flow<Unit> = callbackFlow {
        auth.signOut()
        trySend(Unit)
        awaitClose()
    }.catch { throw GuardianApiException.GenericErrorException }

    override suspend fun isUserLogged(): Flow<Boolean> = flow {
        emit(auth.currentUser != null)
    }
}