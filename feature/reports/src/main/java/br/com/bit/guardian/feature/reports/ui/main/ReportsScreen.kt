package br.com.bit.guardian.feature.reports.ui.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.reports.ui.widget.HomeReportWidgetRoute

@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier
) {
    HomeReportWidgetRoute(modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetPreview() {
    GuardianTheme {
        ReportsScreen()
    }
}