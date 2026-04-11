package br.com.bit.guardian.core.ui.composable.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TitleWithIcon
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun HomeContentWidget(
    modifier: Modifier = Modifier,
    title: Int,
    icon: Int = GuardianIcon.Devices,
    backgroundIcon: Int,
    iconColor: Color,
    backgroundIconColor: Color,
    boxBackgroundColor: Color? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable () -> Unit = {}
) {
    Column(
        modifier = modifier.animateContentSize()
    ) {
        TitleWithIcon(
            modifier = Modifier,
            title = title,
            icon = icon,
            backgroundIcon = backgroundIcon,
            iconColor = iconColor,
            backgroundIconColor = backgroundIconColor
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp)
                .background(
                    boxBackgroundColor ?: GuardianTheme.colors.primaryContainer,
                    RoundedCornerShape(8.dp)
                )
                .padding(GuardianTheme.dimens.spacingXS),
            contentAlignment = contentAlignment
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeDeviceWidgetPreview() {
    GuardianTheme {
        HomeContentWidget(
            title = br.com.bit.guardian.core.designsystem.R.string.ds_app_name,
            icon = GuardianIcon.Devices,
            backgroundIcon = br.com.bit.guardian.core.designsystem.R.drawable.ds_bg_polygon,
            iconColor = GuardianTheme.colors.iconActiveColor,
            backgroundIconColor = GuardianTheme.colors.error
        )
    }
}