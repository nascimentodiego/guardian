package br.com.bit.guardian.app.ui.destination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.AdaptiveStatusBarStyle
import br.com.bit.guardian.settings.ui.profile.SettingsRoute

@Composable
fun SettingDestination() {
    AdaptiveStatusBarStyle()
    Column(modifier = Modifier.statusBarsPadding()) {
        SettingsRoute()
    }
}