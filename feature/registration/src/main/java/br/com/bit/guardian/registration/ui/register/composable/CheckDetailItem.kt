package br.com.bit.guardian.registration.ui.register.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.registration.R

@Composable
fun CheckDetailItem(
    modifier: Modifier = Modifier,
    isSuccess: Boolean = false,
    @StringRes textRes: Int
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    val color = if (isSuccess) GuardianTheme.colors.success else GuardianTheme.colors.onBackground
    val icon = if (isSuccess) GuardianIcon.CheckCircle else GuardianIcon.UnCheckCircle

    Icon(
        painter = painterResource(id = icon),
        contentDescription = null,
        tint = color,
        modifier = Modifier.size(14.dp)
    )
    Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingXXS))
    TextBodySmall(stringRes = textRes, color = color)
}

@Preview
@Composable
fun CheckDetailItemPreview() {
    CheckDetailItem(
        isSuccess = false,
        textRes = R.string.login_register_rule_different_from_email
    )
}