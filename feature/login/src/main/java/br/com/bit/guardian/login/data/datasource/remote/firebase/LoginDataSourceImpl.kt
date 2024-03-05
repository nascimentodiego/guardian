package br.com.bit.guardian.login.data.datasource.remote.firebase

import br.com.bit.guardian.login.data.datasource.remote.LoginDataSource
import br.com.bit.guardian.login.data.datasource.remote.response.UserLoginResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginDataSourceImpl @Inject constructor(private val auth: FirebaseAuth) : LoginDataSource {
    override suspend fun createUser(email: String, password: String): Flow<UserLoginResponse> =
        callbackFlow {
            auth.createUserWithEmailAndPassword("diego.nasc@gmai.com", "123456789")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        trySend(
                            UserLoginResponse(
                                name = task.result.user?.displayName.orEmpty(),
                                email = task.result.user?.email.orEmpty(),
                                photoUrl = task.result.user?.phoneNumber.orEmpty()
                            )
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    close()
                    throw exception
                }
            awaitClose()
        }
}