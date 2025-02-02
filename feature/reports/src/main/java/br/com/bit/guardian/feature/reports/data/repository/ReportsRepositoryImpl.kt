package br.com.bit.guardian.feature.reports.data.repository

import br.com.bit.guardian.core.common.network.exceptions.handleNetworkError
import br.com.bit.guardian.feature.reports.data.datasource.ReportsDataSource
import br.com.bit.guardian.feature.reports.data.repository.model.Report
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ReportsRepositoryImpl @Inject constructor(
    private val datasource: ReportsDataSource
) : ReportsRepository {
    override fun fetchReports(): Flow<List<Report>> = flow {
        datasource.fetchReports().collect { response ->
            emit(response.map { Report(it.deviceName, it.date, it.action) })
        }
    }.flowOn(Dispatchers.IO).handleNetworkError()
}