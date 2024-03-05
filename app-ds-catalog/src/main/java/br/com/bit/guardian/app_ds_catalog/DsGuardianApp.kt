package br.com.bit.guardian.app_ds_catalog

import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import br.com.bit.guardian.app_ds_catalog.model.tabs
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun DsGuardiansApp(
    widthSizeClass: WindowWidthSizeClass,
) {
    GuardianTheme {
        when (widthSizeClass){
            WindowWidthSizeClass.Compact ->  MainScreen(tabs)
            else -> Text(text = "Expandida")
        }
    }
}