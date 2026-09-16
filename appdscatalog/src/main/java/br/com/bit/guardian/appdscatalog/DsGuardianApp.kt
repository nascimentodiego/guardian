package br.com.bit.guardian.appdscatalog

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.com.bit.guardian.appdscatalog.model.tabs
import br.com.bit.guardian.core.designsystem.adaptive.LayoutMode
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun DsGuardiansApp(
    layoutMode: LayoutMode
) {
    GuardianTheme {
        when (layoutMode) {
            LayoutMode.Compact -> MainScreen(tabs)
            else -> Text(text = "Expandida")
        }
    }
}
