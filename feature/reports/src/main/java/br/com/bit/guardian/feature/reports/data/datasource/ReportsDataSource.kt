package br.com.bit.guardian.feature.reports.data.datasource

import br.com.bit.guardian.feature.reports.data.datasource.remote.response.ReportResponse
import kotlinx.coroutines.flow.Flow

interface ReportsDataSource {
    suspend fun fetchReports(): Flow<List<ReportResponse>>
}