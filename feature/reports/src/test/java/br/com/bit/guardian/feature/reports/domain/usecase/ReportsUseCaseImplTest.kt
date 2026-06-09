package br.com.bit.guardian.feature.reports.domain.usecase

import br.com.bit.guardian.feature.reports.data.repository.model.Report
import br.com.bit.guardian.feature.reports.util.fakes.FakeReportsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ReportsUseCaseImplTest {

    private lateinit var fakeRepository: FakeReportsRepository
    private lateinit var useCase: ReportsUseCaseImpl

    @Before
    fun setup() {
        fakeRepository = FakeReportsRepository()
        useCase = ReportsUseCaseImpl(fakeRepository)
    }

    // — invoke —

    @Test
    fun `invoke maps repository report to domain entity`() = runTest {
        fakeRepository.fetchReportsResult = flowOf(
            listOf(
                Report(
                    deviceName = "Garage",
                    dateTime = "2024-01-15T12:30:00Z",
                    action = "OPEN_CLOSE"
                )
            )
        )

        val result = useCase().first()

        assertEquals(1, result.size)
        assertEquals("Garage", result[0].deviceName)
        assertEquals("OPEN_CLOSE", result[0].action)
    }

    @Test
    fun `invoke delegates to repository fetchReports`() = runTest {
        var called = false
        val repoWithCapture = object : FakeReportsRepository() {
            override fun fetchReports(): Flow<List<Report>> {
                called = true
                return flowOf(emptyList())
            }
        }

        ReportsUseCaseImpl(repoWithCapture).invoke().first()

        assertTrue(called)
    }

    @Test
    fun `invoke returns empty list when repository returns no reports`() = runTest {
        fakeRepository.fetchReportsResult = flowOf(emptyList())

        val result = useCase().first()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `invoke maps multiple reports to domain entities`() = runTest {
        fakeRepository.fetchReportsResult = flowOf(
            listOf(
                Report(
                    deviceName = "Garage",
                    dateTime = "2024-01-15T12:00:00Z",
                    action = "OPEN_CLOSE"
                ),
                Report(deviceName = "Camera", dateTime = "2024-01-15T13:00:00Z", action = "DETECT")
            )
        )

        val result = useCase().first()

        assertEquals(2, result.size)
        assertEquals("Garage", result[0].deviceName)
        assertEquals("Camera", result[1].deviceName)
    }

    @Test
    fun `invoke formats date and time from dateTime field`() = runTest {
        fakeRepository.fetchReportsResult = flowOf(
            listOf(
                Report(deviceName = "Sensor", dateTime = "2024-01-15T12:30:00Z", action = "ALARM")
            )
        )

        val result = useCase().first()

        assertTrue(result[0].date.isNotEmpty())
        assertTrue(result[0].time.isNotEmpty())
    }
}
