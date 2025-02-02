package br.com.bit.guardian.feature.reports.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.feature.reports.ui.ReportsViewModel
import br.com.bit.guardian.feature.reports.ui.widget.model.ReportsUiState

@Composable
fun HomeReportWidgetRoute(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<ReportsViewModel>()

    val uiState = if (LocalInspectionMode.current)
        ReportsUiState.Loading
    else
        viewModel.uiState.collectAsStateWithLifecycle().value

    HomeReportsWidget(
        modifier = modifier,
        uiState = uiState,
        onRetry = viewModel::fetchReports
    )
}