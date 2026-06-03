package br.com.bit.guardian.core.test

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import br.com.bit.guardian.core.designsystem.theme.GuardianWindowSize

/**
 * Computes the current [GuardianWindowSize] from [LocalConfiguration], allowing
 * tests with different Robolectric qualifiers to produce the correct window
 * sizes without relying on experimental APIs.
 */
@Composable
fun rememberGuardianWindowSizeFromConfig(): GuardianWindowSize {
    val configuration = LocalConfiguration.current
    return remember(configuration.screenWidthDp, configuration.screenHeightDp) {
        val widthClass = when {
            configuration.screenWidthDp < 600 -> WindowWidthSizeClass.Compact
            configuration.screenWidthDp < 840 -> WindowWidthSizeClass.Medium
            else -> WindowWidthSizeClass.Expanded
        }
        val heightClass = when {
            configuration.screenHeightDp < 480 -> WindowHeightSizeClass.Compact
            configuration.screenHeightDp < 900 -> WindowHeightSizeClass.Medium
            else -> WindowHeightSizeClass.Expanded
        }
        GuardianWindowSize(widthClass, heightClass)
    }
}
