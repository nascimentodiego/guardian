<<<<<<<< HEAD:feature/settings/src/main/java/br/com/bit/guardian/settings/ui/home/HomePhonesWidget.kt
package br.com.bit.guardian.settings.ui.home
========
package br.com.bit.guardian.settings.home.ui
>>>>>>>> 872c452b6c08caebe79d09f208acf4f4c48ac89f:feature/settings/src/main/java/br/com/bit/guardian/settings/home/ui/HomePhonesWidget.kt

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.R as Rds
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.settings.R

@Composable
fun HomePhonesWidget(
    modifier: Modifier = Modifier
) {
    HomeContentWidget(
        modifier = modifier,
        title = R.string.settings_phone_title,
        icon = GuardianIcon.Phone,
        backgroundIcon = Rds.drawable.ds_bg_circle,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.success
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun HomeReportsWidgetPreview() {
    GuardianTheme {
        HomePhonesWidget()
    }
}