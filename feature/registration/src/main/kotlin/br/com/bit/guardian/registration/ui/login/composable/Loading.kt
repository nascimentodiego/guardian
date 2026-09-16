package br.com.bit.guardian.registration.ui.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.adaptive.LayoutMode
import br.com.bit.guardian.core.designsystem.adaptive.LocalAdaptiveLayout
import br.com.bit.guardian.core.designsystem.extension.GuardianThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val layout = LocalAdaptiveLayout.current
    if (layout.mode == LayoutMode.Compact) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GuardianIcon()
            Title()
        }
    } else {
        Row(verticalAlignment = Alignment.CenterVertically) {
            GuardianIcon()
            Divider(
                modifier = Modifier
                    .width(2.dp)
                    .height(72.dp)
                    .background(Color.White)
            )
            Spacer(modifier = Modifier.width(16.dp))
            TitleLarge()
        }
    }
}

@Composable
private fun GuardianIcon() {
    Icon(
        modifier = Modifier.size(128.dp),
        painter = painterResource(id = R.drawable.ds_ic_guardian),
        tint = GuardianTheme.colors.iconActiveColor,
        contentDescription = null
    )
}

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.titleLarge,
        color = GuardianTheme.colors.iconActiveColor
    )
}

@Composable
fun TitleLarge() {
    Text(
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.titleLarge,
        fontSize = 72.sp,
        color = GuardianTheme.colors.iconActiveColor
    )
}

@GuardianThemePreviews
@Composable
fun LoadingScreenPreview() {
    GuardianTheme {
        LoadingScreen()
    }
}