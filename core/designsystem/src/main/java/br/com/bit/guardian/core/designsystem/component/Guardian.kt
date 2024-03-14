package br.com.bit.guardian.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun GuardianLogoLarge(
    modifier: Modifier = Modifier,
    tintColor: Color =GuardianTheme.colors.onBackground
) {
    Icon(
        modifier = modifier.size(256.dp),
        painter = painterResource(id = R.drawable.ds_ic_guardian),
        tint = tintColor,
        contentDescription = null
    )
}

@Composable
fun GuardianLogoMedium(
    modifier: Modifier = Modifier,
    tintColor: Color =GuardianTheme.colors.onBackground
) {
    Icon(
        modifier = modifier.size(128.dp),
        painter = painterResource(id = R.drawable.ds_ic_guardian),
        tint = tintColor,
        contentDescription = null
    )
}

@Composable
fun GuardianLogoSmall(
    modifier: Modifier = Modifier,
    iconSize:Dp = 72.dp,
    tintColor: Color = GuardianTheme.colors.onBackground
) {
    Icon(
        modifier = modifier.size(iconSize),
        painter = painterResource(id = R.drawable.ds_ic_guardian),
        tint = tintColor,
        contentDescription = null
    )
}

@Composable
fun GuardianDisplayMedium(
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.onBackground
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.displayMedium,
        color = color
    )
}

@Composable
fun GuardianTitleLarge(
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.onBackground
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.displayLarge,
        color = color
    )
}
