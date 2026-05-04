package br.com.bit.guardian.management.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.extension.GuardianThemePreviews
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.management.R
import br.com.bit.guardian.core.designsystem.R as Rds

@Composable
fun HomeDeviceWidget(
    modifier: Modifier = Modifier
) {
    HomeContentWidget(
        modifier = modifier,
        title = R.string.title,
        icon = GuardianIcon.Devices,
        backgroundIcon = Rds.drawable.ds_bg_polygon,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.error
    )
}

@GuardianThemePreviews
@Composable
fun HomeDeviceWidgetPreview() {
    GuardianTheme {
        HomeDeviceWidget()
    }
}