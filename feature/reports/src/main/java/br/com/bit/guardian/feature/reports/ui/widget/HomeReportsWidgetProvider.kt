package br.com.bit.guardian.feature.reports.ui.widget

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.bit.guardian.core.common.ExcludeFromGeneratedReport
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceAction
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceType
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState

@ExcludeFromGeneratedReport
class HomeReportsWidgetProvider : PreviewParameterProvider<ReportsUiState> {
    override val values: Sequence<ReportsUiState> = sequenceOf(
        ReportsUiState.Loading,
        ReportsUiState.Success(
            listOf(
                ActivityLog(
                    key = "1",
                    date = "15/01",
                    time = "10:30",
                    deviceName = "Portão da Garagem",
                    deviceType = DeviceType.BIT_GARAGE,
                    action = DeviceAction.OPEN_CLOSE
                ),
                ActivityLog(
                    key = "2",
                    date = "15/01",
                    time = "09:00",
                    deviceName = "Sensor PIR",
                    deviceType = DeviceType.BIT_PIR,
                    action = DeviceAction.DETECT
                )
            )
        ),
        ReportsUiState.Success(emptyList()),
        ReportsUiState.Error
    )
}
