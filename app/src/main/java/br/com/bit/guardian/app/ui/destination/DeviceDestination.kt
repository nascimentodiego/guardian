package br.com.bit.guardian.app.ui.destination

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.bit.guardian.core.designsystem.theme.AdaptiveStatusBarStyle

@Composable
fun DeviceDestination() {
    AdaptiveStatusBarStyle()
    Column(modifier = Modifier.statusBarsPadding()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Red)
                .statusBarsPadding()
        ) {
        }
    }
}