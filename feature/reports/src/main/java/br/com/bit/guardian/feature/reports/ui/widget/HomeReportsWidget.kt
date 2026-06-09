package br.com.bit.guardian.feature.reports.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.common.ExcludeFromGeneratedReport
import br.com.bit.guardian.core.designsystem.component.LoadingComponent
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.reports.R
import br.com.bit.guardian.feature.reports.ui.widget.composables.ErrorReportList
import br.com.bit.guardian.feature.reports.ui.widget.composables.SuccessReportList
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState.Error
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState.Loading
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState.Success
import br.com.bit.guardian.core.designsystem.R as Rds

@Composable
fun HomeReportsWidget(
    modifier: Modifier = Modifier,
    uiState: ReportsUiState?,
    onRetry: () -> Unit = {}
) {
    HomeContentWidget(
        modifier = modifier,
        title = R.string.reports_title,
        icon = GuardianIcon.Reports,
        backgroundIcon = Rds.drawable.ds_bg_quadrangle,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.warning,
        boxBackgroundColor = GuardianTheme.colors.surface,
        contentAlignment = Alignment.TopStart
    ) {
        uiState?.let {
            when (it) {
                is Loading -> LoadingComponent(width = 120.dp, height = 100.dp)
                is Success -> SuccessReportList(it.data)
                is Error -> ErrorReportList { onRetry.invoke() }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
@ExcludeFromGeneratedReport
fun HomeReportsWidgetPreview(
    @PreviewParameter(HomeReportsWidgetProvider::class) uiState: ReportsUiState
) {
    GuardianTheme {
        HomeReportsWidget(uiState = uiState)
    }
}