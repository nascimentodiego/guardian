package br.com.bit.guardian.feature.reports.data.repository

import br.com.bit.guardian.feature.reports.data.repository.model.Report
import kotlinx.coroutines.flow.Flow

interface ReportsRepository {
    fun fetchReports(): Flow<List<Report>>
}
