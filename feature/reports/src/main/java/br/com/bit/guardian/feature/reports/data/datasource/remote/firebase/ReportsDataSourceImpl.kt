package br.com.bit.guardian.feature.reports.data.datasource.remote.firebase

import br.com.bit.guardian.core.common.network.exceptions.GuardianApiException
import br.com.bit.guardian.feature.reports.data.datasource.ReportsDataSource
import br.com.bit.guardian.feature.reports.data.datasource.remote.response.ReportResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class ReportsDataSourceImpl @Inject constructor(
    private val database: FirebaseDatabase,
    private val auth: FirebaseAuth
) : ReportsDataSource {
    override suspend fun fetchReports(): Flow<List<ReportResponse>> =
        callbackFlow {
            val uuid = auth.currentUser?.uid

            database.reference
                .child("users")
                .child(uuid.orEmpty())
                .child("logs")
                .orderByKey()
                .limitToLast(10)
                .get()
                .addOnSuccessListener {
                    try {
                        trySend(
                            it.children.map { snapshot ->
                                snapshot.key
                                val report = snapshot.getValue(ReportResponse::class.java)
                                report!!
                            }
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
}