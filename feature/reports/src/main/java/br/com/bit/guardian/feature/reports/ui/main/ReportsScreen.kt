package br.com.bit.guardian.feature.reports.ui.main

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.reports.R
import br.com.bit.guardian.feature.reports.ui.widget.composables.ActivityColumnComponent

@Composable
fun ReportsScreen(
    modifier: Modifier = Modifier
) {
    HomeContentWidget(
        modifier = modifier,
        title = R.string.reports_title,
        icon = GuardianIcon.Reports,
        backgroundIcon = br.com.bit.guardian.core.designsystem.R.drawable.ds_bg_quadrangle,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.warning,
        boxBackgroundColor = GuardianTheme.colors.surface,
        contentAlignment = Alignment.Center
    ) {
//        EmptyComponent()

        LazyColumn {
            items(5) {
                ActivityColumnComponent(modifier = Modifier)
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetPreview() {
    GuardianTheme {
        ReportsScreen()
    }
}