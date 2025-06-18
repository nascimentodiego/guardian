package br.com.bit.guardian.app.ui.home.compact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextTitleMedium
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.animation.Duration
import br.com.bit.guardian.core.ui.animation.VerticallyAnimatedContent
import br.com.bit.guardian.feature.reports.ui.widget.HomeReportWidgetRoute
import br.com.bit.guardian.management.widget.HomeDeviceWidget
import br.com.bit.guardian.settings.ui.home.HomePhonesWidget
import br.com.bit.guardian.core.designsystem.R as Rds

@Composable
fun HomeCompact() {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 12.dp, 12.dp))
                .background(GuardianTheme.colors.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = ImageVector.vectorResource(id = Rds.drawable.ds_bg_quadrangle)
            val painterIcon = rememberVectorPainter(image = icon)
            val iconColor = GuardianTheme.colors.warning
            Spacer(
                modifier = Modifier
                    .drawBehind {
                        with(painterIcon) {
                            translate(
                                left = -110.toDp().toPx(),
                                top = -130.toDp().toPx()
                            ) {
                                draw(
                                    size = Size(
                                        250.toDp().toPx(),
                                        250.toDp().toPx()
                                    ),
                                    colorFilter = ColorFilter.tint(color = iconColor)
                                )
                            }
                        }
                    }
            )
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(GuardianIcon.GuardianIcon),
                contentDescription = null,
                tint = GuardianTheme.colors.onPrimary
            )
            TextTitleMedium(
                titleRes = Rds.string.ds_app_name,
                color = GuardianTheme.colors.onPrimary
            )
        }
        Spacer(
            modifier = Modifier
                .height(GuardianTheme.dimens.spacingM)
                .padding(horizontal = GuardianTheme.dimens.spacingM)
        )

        VerticallyAnimatedContent {
            // Devices
            HomeDeviceWidget(modifier = Modifier.padding(horizontal = GuardianTheme.dimens.spacingM))
        }

        // telephones
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        VerticallyAnimatedContent(
            delayAnimation = Duration.SECOND_START
        ) {
            HomePhonesWidget(modifier = Modifier.padding(horizontal = GuardianTheme.dimens.spacingM))
        }
        //Activities
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        VerticallyAnimatedContent(
            delayAnimation = Duration.THIRD_START
        ) {
            HomeReportWidgetRoute(
                modifier = Modifier.padding(horizontal = GuardianTheme.dimens.spacingM)
            )
        }
    }
}

@Preview(device = "spec:width=411dp,height=891dp", showBackground = true)
@Composable
fun HomeCompactPreview() {
    GuardianTheme {
        HomeCompact()
    }
}