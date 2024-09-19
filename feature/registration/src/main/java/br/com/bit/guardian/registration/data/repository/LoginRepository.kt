package br.com.bit.guardian.registration.data.repository

import br.com.bit.guardian.registration.data.repository.model.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    fun createUser(email: String, password: String): Flow<User>
    fun signIn(email: String, password: String): Flow<User>
    fun signOut(): Flow<Unit>
    fun isUserLogged(): Flow<Boolean>
}