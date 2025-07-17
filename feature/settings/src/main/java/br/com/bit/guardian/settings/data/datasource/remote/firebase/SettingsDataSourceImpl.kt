package br.com.bit.guardian.settings.data.datasource.remote.firebase

import br.com.bit.guardian.core.common.network.exceptions.GuardianApiException
import br.com.bit.guardian.settings.data.datasource.SettingsDataSource
import br.com.bit.guardian.settings.data.datasource.remote.response.SettingsResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class SettingsDataSourceImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth
) : SettingsDataSource {
    override suspend fun fetchSettings(): Flow<SettingsResponse> =
        callbackFlow {
            val uuid = auth.currentUser?.uid

            database.reference
                .child("users")
                .child(uuid.orEmpty())
                .child("settings")
                .get()
                .addOnSuccessListener {
                    try {
                        trySend(
                            it.getValue(SettingsResponse::class.java)
                                ?: throw GuardianApiException.GenericErrorException
                        )
                    } catch (e: Exception) {
                        close(e)
                    }
                }.addOnFailureListener { exception ->
                    close(exception)
                }
            awaitClose()
        }.catch { error ->
            error.cause?.let { throw it }
            throw GuardianApiException.GenericErrorException
        }

    override suspend fun saveAvatar(avatarId: Int): Flow<Unit> = callbackFlow {
        val uuid = auth.currentUser?.uid
        database.reference
            .child("users")
            .child(uuid.orEmpty())
            .child("settings")
            .child("avatar").setValue(avatarId)
            .addOnSuccessListener {
                trySend(Unit)
            }.addOnFailureListener { exception ->
                close(exception)
            }
        awaitClose()
    }.catch { error ->
        error.cause?.let { throw it }
        throw GuardianApiException.GenericErrorException
    }

    override suspend fun saveNickName(nickname: String): Flow<Unit> = callbackFlow {
        val uuid = auth.currentUser?.uid
        database.reference
            .child("users")
            .child(uuid.orEmpty())
            .child("settings")
            .child("nickname").setValue(nickname)
            .addOnSuccessListener {
                trySend(Unit)
            }.addOnFailureListener { exception ->
                close(exception)
            }
        awaitClose()
    }.catch { error ->
        error.cause?.let { throw it }
        throw GuardianApiException.GenericErrorException
    }

    override suspend fun signOut(): Flow<Unit> = callbackFlow {
        auth.signOut()
        trySend(Unit)
        awaitClose()
    }.catch { throw GuardianApiException.GenericErrorException }
}