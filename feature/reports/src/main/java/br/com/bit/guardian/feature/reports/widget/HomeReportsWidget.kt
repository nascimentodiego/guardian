package br.com.bit.guardian.feature.reports.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.R as Rds
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.reports.R

@Composable
fun HomeReportsWidget(
    modifier: Modifier = Modifier
) {
    HomeContentWidget(
        modifier = modifier,
        title = R.string.reports_title,
        icon = GuardianIcon.Reports,
        backgroundIcon = Rds.drawable.ds_bg_quadrangle,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.warning
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetPreview() {
    GuardianTheme {
        HomeReportsWidget()
    }
}