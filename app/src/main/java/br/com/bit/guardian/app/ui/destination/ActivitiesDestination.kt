package br.com.bit.guardian.app.ui.destination

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.AdaptiveStatusBarStyle
import br.com.bit.guardian.feature.reports.ui.widget.ReportsRoute

@Composable
fun ActivitiesDestination() {
    AdaptiveStatusBarStyle()
    Column(modifier = Modifier.statusBarsPadding()) {
        ReportsRoute()
    }
}