package br.com.bit.guardian.feature.reports.ui.widget.composables

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.component.TextHeadLineSmall
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog

@Composable
fun ActivityColumnComponent(
    modifier: Modifier = Modifier,
    activityLog: ActivityLog
) {
    Column(
        modifier = modifier
            .background(GuardianTheme.colors.primaryContainer, RoundedCornerShape(4.dp))
            .padding(bottom = GuardianTheme.dimens.spacingXS)
            .clip(RoundedCornerShape(4.dp))
    ) {
        Row(
            modifier = Modifier
                .background(GuardianTheme.colors.primaryContainer)
                .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier) {
                val circleColor = GuardianTheme.colors.warning
                val density = LocalDensity.current
                val circleSize = with(density) { 24.dp.toPx() }
                Spacer(
                    modifier = Modifier.drawBehind {
                        drawCircle(color = circleColor, radius = circleSize)
                    }
                )
                Icon(
                    painter = painterResource(id = GuardianIcon.Devices),
                    contentDescription = null,
                    tint = GuardianTheme.colors.primary
                )
            }
            TextHeadLineSmall(
                title = activityLog.deviceName,
                color = GuardianTheme.colors.textTitle,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Column(
            modifier = Modifier.padding(horizontal = GuardianTheme.dimens.spacingXXS)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingXXS),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = GuardianIcon.Calendar),
                    modifier = Modifier.size(14.dp),
                    contentDescription = null,
                    tint = GuardianTheme.colors.iconInactiveColor
                )
                TextBodySmall(text = activityLog.date, color = GuardianTheme.colors.textBody)
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
            Row(
                horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingXXS),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = GuardianIcon.Timer),
                    modifier = Modifier.size(14.dp),
                    contentDescription = null,
                    tint = GuardianTheme.colors.iconInactiveColor
                )
                TextBodySmall(text = activityLog.time, color = GuardianTheme.colors.textBody)
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = GuardianTheme.dimens.spacingXS),
                color = GuardianTheme.colors.iconInactiveColor
            )
            TextHeadLineSmall(
                title = stringResource(activityLog.action.resourceName),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = GuardianTheme.colors.textBody
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ActivityComponentPreview() {
    GuardianTheme {
        ActivityColumnComponent(activityLog = ActivityLog.empty())
    }
}