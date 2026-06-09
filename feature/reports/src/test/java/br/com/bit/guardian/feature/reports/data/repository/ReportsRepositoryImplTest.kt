package br.com.bit.guardian.feature.reports.data.repository

import br.com.bit.guardian.feature.reports.data.datasource.remote.response.Device
import br.com.bit.guardian.feature.reports.data.datasource.remote.response.ReportResponse
import br.com.bit.guardian.feature.reports.util.fakes.FakeReportsDataSource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ReportsRepositoryImplTest {

    private lateinit var fakeDataSource: FakeReportsDataSource
    private lateinit var repository: ReportsRepositoryImpl

    @Before
    fun setup() {
        fakeDataSource = FakeReportsDataSource()
        repository = ReportsRepositoryImpl(fakeDataSource)
    }

    // — fetchReports —

    @Test
    fun `fetchReports returns mapped report on success`() = runTest {
        fakeDataSource.fetchReportsResult = flowOf(
            listOf(
                ReportResponse(
                    date_time = "2024-01-15T10:30:00Z",
                    device = Device(name = "Garage"),
                    action = "OPEN_CLOSE"
                )
            )
        )

        val result = repository.fetchReports().first()

        assertEquals(1, result.size)
        assertEquals("Garage", result[0].deviceName)
        assertEquals("2024-01-15T10:30:00Z", result[0].dateTime)
        assertEquals("OPEN_CLOSE", result[0].action)
    }

    @Test
    fun `fetchReports maps null action to empty string`() = runTest {
        fakeDataSource.fetchReportsResult = flowOf(
            listOf(
                ReportResponse(
                    date_time = "2024-01-15T10:30:00Z",
                    device = Device(name = "Camera"),
                    action = null
                )
            )
        )

        val result = repository.fetchReports().first()

        assertEquals("", result[0].action)
    }

    @Test
    fun `fetchReports maps device name from response`() = runTest {
        fakeDataSource.fetchReportsResult = flowOf(
            listOf(
                ReportResponse(
                    date_time = "2024-01-15T10:30:00Z",
                    device = Device(name = "Front Door"),
                    action = "ALARM"
                )
            )
        )

        val result = repository.fetchReports().first()

        assertEquals("Front Door", result[0].deviceName)
    }

    @Test
    fun `fetchReports returns empty list when datasource returns no reports`() = runTest {
        fakeDataSource.fetchReportsResult = flowOf(emptyList())

        val result = repository.fetchReports().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `fetchReports maps multiple reports correctly`() = runTest {
        fakeDataSource.fetchReportsResult = flowOf(
            listOf(
                ReportResponse(
                    date_time = "2024-01-15T10:30:00Z",
                    device = Device(name = "Garage"),
                    action = "OPEN_CLOSE"
                ),
                ReportResponse(
                    date_time = "2024-01-15T11:00:00Z",
                    device = Device(name = "Camera"),
                    action = "DETECT"
                )
            )
        )

        val result = repository.fetchReports().first()

        assertEquals(2, result.size)
        assertEquals("Garage", result[0].deviceName)
        assertEquals("Camera", result[1].deviceName)
    }
}
