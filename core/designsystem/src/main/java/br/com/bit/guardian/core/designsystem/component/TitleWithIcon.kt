package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.R as RDs
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.icon.animations.GeometricAnimatedIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun TitleWithIcon(
    modifier: Modifier,
    @StringRes title: Int,
    @DrawableRes icon: Int = GuardianIcon.Devices,
    @DrawableRes backgroundIcon: Int = RDs.drawable.ds_bg_polygon,
    iconColor: Color,
    backgroundIconColor: Color
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingXXS)
    ) {
        GeometricAnimatedIcon(
            modifier = Modifier,
            icon = icon,
            backgroundIcon = backgroundIcon,
            iconColor = iconColor,
            backgroundIconColor = backgroundIconColor
        )
        TextTitleSmall(titleRes = title, color = GuardianTheme.colors.textTitle)
    }
}

@Preview
@Composable
fun TitleWithIconPreview() {
    GuardianTheme {
        TitleWithIcon(
            modifier = Modifier,
            title = RDs.string.ds_app_name,
            icon = GuardianIcon.Devices,
            backgroundIcon = RDs.drawable.ds_bg_polygon,
            iconColor = GuardianTheme.colors.iconActiveColor,
            backgroundIconColor = GuardianTheme.colors.error
        )
    }
}