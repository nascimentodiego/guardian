package br.com.bit.guardian.login.ui.composable

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
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    val widthSize = LocalWindowSizeClass.current
    if (widthSize.widthSizeClass == WindowWidthSizeClass.Compact) {
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
        modifier = Modifier.size(256.dp),
        painter = painterResource(id = R.drawable.ds_ic_guardian),
        tint = GuardianTheme.colors.onPrimary,
        contentDescription = null
    )
}

@Composable
fun Title() {
    Text(
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.titleLarge,
        color = Color.White
    )
}

@Composable
fun TitleLarge() {
    Text(
        text = stringResource(id = R.string.ds_app_name),
        style = GuardianTheme.typography.titleLarge,
        fontSize = 92.sp,
        color = Color.White
    )
}

@ThemePreviews
@Composable
fun LoadingScreenPreview() {
    GuardianTheme {
        LoadingScreen()
    }
}