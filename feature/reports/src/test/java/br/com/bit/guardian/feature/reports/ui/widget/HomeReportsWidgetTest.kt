package br.com.bit.guardian.feature.reports.ui.widget

import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import br.com.bit.guardian.core.test.ParameterizedComposeScreenTest
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceAction
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceType
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.ParameterizedRobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(ParameterizedRobolectricTestRunner::class)
@Config(sdk = [34])
class HomeReportsWidgetTest(qualifier: String) : ParameterizedComposeScreenTest(qualifier) {

    private fun setContent(
        uiState: ReportsUiState? = ReportsUiState.Loading,
        onRetry: () -> Unit = {}
    ) {
        setScreenContent {
            HomeReportsWidget(uiState = uiState, onRetry = onRetry)
        }
    }

    // — Title —

    @Test
    fun `HomeReportsWidget shows title when state is Loading`() {
        setContent(uiState = ReportsUiState.Loading)
        composeRule.onNodeWithText("Atividades").assertExists()
    }

    @Test
    fun `HomeReportsWidget shows title when state is Success`() {
        setContent(uiState = ReportsUiState.Success(emptyList()))
        composeRule.onNodeWithText("Atividades").assertExists()
    }

    @Test
    fun `HomeReportsWidget shows title when state is Error`() {
        setContent(uiState = ReportsUiState.Error)
        composeRule.onNodeWithText("Atividades").assertExists()
    }

    // — Loading state —

    @Test
    fun `HomeReportsWidget does not show empty message when state is Loading`() {
        setContent(uiState = ReportsUiState.Loading)
        composeRule.onNodeWithText("Nenhuma atividade encontrada").assertDoesNotExist()
    }

    @Test
    fun `HomeReportsWidget does not show error when state is Loading`() {
        setContent(uiState = ReportsUiState.Loading)
        composeRule.onNodeWithText("Falha ao enviar a requisição !").assertDoesNotExist()
    }

    // — Success state with data —

    @Test
    fun `HomeReportsWidget shows device name when state is Success with data`() {
        val log = ActivityLog(
            key = "1",
            date = "15/01",
            time = "10:30",
            deviceName = "Sensor de Movimento",
            deviceType = DeviceType.BIT_PIR,
            action = DeviceAction.DETECT
        )
        setContent(uiState = ReportsUiState.Success(listOf(log)))
        composeRule.onNodeWithText("Sensor de Movimento").assertExists()
    }

    @Test
    fun `HomeReportsWidget shows date when state is Success with data`() {
        val log = ActivityLog(
            key = "2",
            date = "20/02",
            time = "08:00",
            deviceName = "Portão",
            deviceType = DeviceType.BIT_GARAGE,
            action = DeviceAction.OPEN_CLOSE
        )
        setContent(uiState = ReportsUiState.Success(listOf(log)))
        composeRule.onNodeWithText("20/02").assertExists()
    }

    @Test
    fun `HomeReportsWidget does not show empty message when state is Success with data`() {
        val log = ActivityLog(
            key = "1",
            date = "15/01",
            time = "10:30",
            deviceName = "Garage",
            deviceType = DeviceType.BIT_GARAGE,
            action = DeviceAction.OPEN_CLOSE
        )
        setContent(uiState = ReportsUiState.Success(listOf(log)))
        composeRule.onNodeWithText("Nenhuma atividade encontrada").assertDoesNotExist()
    }

    // — Success state with empty list —

    @Test
    fun `HomeReportsWidget shows empty message when state is Success with empty list`() {
        setContent(uiState = ReportsUiState.Success(emptyList()))
        composeRule.onNodeWithText("Nenhuma atividade encontrada").assertExists()
    }

    @Test
    fun `HomeReportsWidget does not show error when state is Success with empty list`() {
        setContent(uiState = ReportsUiState.Success(emptyList()))
        composeRule.onNodeWithText("Falha ao enviar a requisição !").assertDoesNotExist()
    }

    // — Error state —

    @Test
    fun `HomeReportsWidget shows error title when state is Error`() {
        setContent(uiState = ReportsUiState.Error)
        composeRule.onNodeWithText("Falha ao enviar a requisição !").assertExists()
    }

    @Test
    fun `HomeReportsWidget shows error description when state is Error`() {
        setContent(uiState = ReportsUiState.Error)
        composeRule.onNodeWithText("Algo não esta certo, não estou passando bem.").assertExists()
    }

    @Test
    fun `HomeReportsWidget shows retry button when state is Error`() {
        setContent(uiState = ReportsUiState.Error)
        composeRule.onNodeWithText("Tentar novamente").assertExists()
    }

    @Test
    fun `HomeReportsWidget retry button click triggers onRetry callback`() {
        var retried = false
        setContent(uiState = ReportsUiState.Error, onRetry = { retried = true })
        composeRule.onNodeWithText("Tentar novamente").performClick()
        assertTrue(retried)
    }

    @Test
    fun `HomeReportsWidget does not show empty message when state is Error`() {
        setContent(uiState = ReportsUiState.Error)
        composeRule.onNodeWithText("Nenhuma atividade encontrada").assertDoesNotExist()
    }

    // — Null state —

    @Test
    fun `HomeReportsWidget does not show empty message when state is null`() {
        setContent(uiState = null)
        composeRule.onNodeWithText("Nenhuma atividade encontrada").assertDoesNotExist()
    }

    @Test
    fun `HomeReportsWidget does not show error when state is null`() {
        setContent(uiState = null)
        composeRule.onNodeWithText("Falha ao enviar a requisição !").assertDoesNotExist()
    }

    companion object {
        @JvmStatic
        @ParameterizedRobolectricTestRunner.Parameters(name = "{0}")
        fun qualifiers(): Collection<Array<Any>> = defaultQualifiers()
    }
}
