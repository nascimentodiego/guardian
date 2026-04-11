package br.com.bit.guardian.app.ui.destination

import androidx.compose.runtime.Composable
import br.com.bit.guardian.app.ui.home.HomeScreen
import br.com.bit.guardian.core.designsystem.theme.AdaptiveStatusBarStyle
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun HomeDestination() {
    AdaptiveStatusBarStyle(GuardianTheme.colors.primary)
    HomeScreen()
}