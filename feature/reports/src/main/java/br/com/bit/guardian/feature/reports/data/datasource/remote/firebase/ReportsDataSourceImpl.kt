package br.com.bit.guardian.feature.reports.data.datasource.remote.firebase

import br.com.bit.guardian.feature.reports.data.datasource.ReportsDataSource
import br.com.bit.guardian.feature.reports.data.datasource.remote.response.ReportResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
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
                .limitToLast(10)
                .get()
                .addOnSuccessListener {
                    trySend(
                        it.children.map { snapshot ->
                            snapshot.key
                            val report = snapshot.getValue(ReportResponse::class.java)
                            report!!
                        }
                    )
                }.addOnFailureListener { exception ->
                    close(exception)
                }
            awaitClose()
        }
}