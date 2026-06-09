package br.com.bit.guardian.feature.reports.ui

import androidx.lifecycle.SavedStateHandle
import br.com.bit.guardian.core.test.MainDispatcherRule
import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState
import br.com.bit.guardian.feature.reports.util.fakes.FakeReportsUseCase
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ReportsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeUseCase: FakeReportsUseCase
    private lateinit var viewModel: ReportsViewModel

    @Before
    fun setup() {
        fakeUseCase = FakeReportsUseCase()
        viewModel = ReportsViewModel(
            savedStateHandle = SavedStateHandle(),
            useCase = fakeUseCase
        )
    }

    @Test
    fun `init publishes success state when no saved state`() {
        assertTrue(viewModel.uiState.value is ReportsUiState.Success)
    }

    // — fetchReports —

    @Test
    fun `fetchReports publishes success state with mapped activity logs`() = runTest {
        val entity = ReportEntity(
            deviceName = "Garage",
            date = "15/01",
            time = "10:30",
            action = "OPEN_CLOSE"
        )
        fakeUseCase.result = flowOf(listOf(entity))

        viewModel.fetchReports()

        val state = viewModel.uiState.value as ReportsUiState.Success
        assertEquals(1, state.data.size)
        assertEquals("Garage", state.data[0].deviceName)
    }

    @Test
    fun `fetchReports publishes success state with empty state returns no reports`() = runTest {
        fakeUseCase.result = flowOf(emptyList())

        viewModel.fetchReports()

        val state = viewModel.uiState.value as ReportsUiState.Success
        assertTrue(state.data.isEmpty())
    }

    @Test
    fun `fetchReports publishes error state when use case fails`() = runTest {
        fakeUseCase.result = flow { throw RuntimeException("Network error") }

        viewModel.fetchReports()

        assertTrue(viewModel.uiState.value is ReportsUiState.Error)
    }

    @Test
    fun `fetchReports maps action to correct DeviceAction`() = runTest {
        val entity = ReportEntity(
            deviceName = "Sensor",
            date = "15/01",
            time = "10:30",
            action = "ALARM"
        )
        fakeUseCase.result = flowOf(listOf(entity))

        viewModel.fetchReports()

        val state = viewModel.uiState.value as ReportsUiState.Success
        assertEquals(
            br.com.bit.guardian.feature.reports.ui.widget.model.DeviceAction.ALARM,
            state.data[0].action
        )
    }

    // — restoreState —

    @Test
    fun `init does not call use case when state is restored`() = runTest {
        var callCount = 0
        val countingUseCase = FakeReportsUseCase().apply {
            result = flow { callCount++; emit(emptyList()) }
        }

        viewModel = ReportsViewModel(
            savedStateHandle = SavedStateHandle(
                mapOf("ReportsViewModel" to viewModel.uiState.value)
            ),
            useCase = countingUseCase
        )

        assertEquals(0, callCount)
    }

    @Test
    fun `init restores saved state when available`() {
        val savedState = viewModel.uiState.value

        val restoredViewModel = ReportsViewModel(
            savedStateHandle = SavedStateHandle(mapOf("ReportsViewModel" to savedState)),
            useCase = FakeReportsUseCase()
        )

        assertEquals(savedState, restoredViewModel.uiState.value)
    }
}
