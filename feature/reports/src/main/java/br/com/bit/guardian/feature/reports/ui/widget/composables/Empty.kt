package br.com.bit.guardian.feature.reports.ui.widget.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.reports.R

@Composable
fun EmptyComponent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingXS)
    ) {
        Icon(
            painter = painterResource(id = GuardianIcon.Empty),
            contentDescription = null,
            tint = GuardianTheme.colors.disable
        )
        TextBodySmall(
            stringRes = R.string.reports_title_empty
        )
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyComponentPreview() {
    GuardianTheme {
        EmptyComponent()
    }
}