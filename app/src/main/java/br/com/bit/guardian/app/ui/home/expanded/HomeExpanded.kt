package br.com.bit.guardian.app.ui.home.expanded

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.animation.VerticallyAnimatedContent
import br.com.bit.guardian.feature.reports.ui.widget.HomeReportWidgetRoute
import br.com.bit.guardian.management.widget.HomeDeviceWidget
import br.com.bit.guardian.settings.home.ui.HomePhonesWidget

@Composable
fun HomeExpanded(
    modifier: Modifier = Modifier
) {
    VerticallyAnimatedContent {
        Column(
            modifier.verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Devices
                HomeDeviceWidget(
                    modifier = Modifier.weight(.8f)
                )

                VerticalDivider(thickness = GuardianTheme.dimens.spacingXS)
                // Phones
                HomePhonesWidget(
                    modifier = Modifier.weight(.2f)
                )
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))

            //Activities
            HomeReportWidgetRoute()
        }
    }
}

@Preview(showBackground = true, device = "id:Nexus 7 2013")
@Composable
fun HomeExpandedPreview() {
    GuardianTheme {
        HomeExpanded()
    }

}