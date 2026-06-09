package br.com.bit.guardian.feature.reports.util.fakes

import br.com.bit.guardian.feature.reports.data.datasource.ReportsDataSource
import br.com.bit.guardian.feature.reports.data.datasource.remote.response.Device
import br.com.bit.guardian.feature.reports.data.datasource.remote.response.ReportResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeReportsDataSource : ReportsDataSource {

    private val defaultResponse = listOf(
        ReportResponse(
            date_time = "2024-01-15T10:30:00Z",
            device = Device(name = "Garage"),
            action = "OPEN_CLOSE"
        )
    )

    var fetchReportsResult: Flow<List<ReportResponse>> = flowOf(defaultResponse)

    override suspend fun fetchReports(): Flow<List<ReportResponse>> = fetchReportsResult
}
