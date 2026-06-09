package br.com.bit.guardian.feature.reports

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.feature.reports.ui.widget.HomeReportsWidgetPreview
import br.com.bit.guardian.feature.reports.ui.widget.HomeReportsWidgetProvider
import com.android.tools.screenshot.PreviewTest

@PreviewTest
@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetScreenshotTest() {
    HomeReportsWidgetPreview(
        uiState = HomeReportsWidgetProvider().values.first()
    )
}
