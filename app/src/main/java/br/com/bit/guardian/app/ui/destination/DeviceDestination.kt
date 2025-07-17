package br.com.bit.guardian.app.ui.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.bit.guardian.core.designsystem.theme.AdaptiveStatusBarStyle
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun DeviceDestination() {
    AdaptiveStatusBarStyle()
    Column (modifier = Modifier.padding(top = GuardianTheme.dimens.spacingS)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
        ) {

        }
    }
}