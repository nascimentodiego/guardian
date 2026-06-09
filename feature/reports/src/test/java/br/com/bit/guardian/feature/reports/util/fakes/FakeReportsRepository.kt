package br.com.bit.guardian.feature.reports.util.fakes

import br.com.bit.guardian.feature.reports.data.repository.ReportsRepository
import br.com.bit.guardian.feature.reports.data.repository.model.Report
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

open class FakeReportsRepository : ReportsRepository {

    var fetchReportsResult: Flow<List<Report>> = flowOf(
        listOf(
            Report(deviceName = "Garage", dateTime = "2024-01-15T10:30:00Z", action = "OPEN_CLOSE")
        )
    )

    override fun fetchReports(): Flow<List<Report>> = fetchReportsResult
}
