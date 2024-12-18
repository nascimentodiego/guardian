package br.com.bit.guardian.feature.reports.ui.widget

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.LoadingComponent
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.reports.R
import br.com.bit.guardian.core.designsystem.R as Rds

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
        backgroundIconColor = GuardianTheme.colors.warning,
        boxBackgroundColor = GuardianTheme.colors.surface,
        contentAlignment = Alignment.Center
    ) {

        //  EmptyComponent()
        LazyRow {
            items(5) {
                LoadingComponent(width = 120.dp, height = 100.dp)
                Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingXS))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetPreview() {
    GuardianTheme {
        HomeReportsWidget()
    }
}