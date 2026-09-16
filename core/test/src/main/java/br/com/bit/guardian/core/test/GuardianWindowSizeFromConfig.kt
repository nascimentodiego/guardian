package br.com.bit.guardian.core.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.window.core.layout.WindowSizeClass
import br.com.bit.guardian.core.designsystem.adaptive.AdaptiveLayoutState
import br.com.bit.guardian.core.designsystem.adaptive.FoldPosture
import br.com.bit.guardian.core.designsystem.adaptive.toLayoutMode

/**
 * Computes the current [AdaptiveLayoutState] from [LocalConfiguration], allowing
 * tests with different Robolectric qualifiers to produce the correct window
 * sizes without a real Activity/WindowManager. Fold posture is always [FoldPosture.Flat] —
 * Robolectric can't simulate hinges.
 */
@Composable
fun rememberAdaptiveLayoutStateFromConfig(): AdaptiveLayoutState {
    val configuration = LocalConfiguration.current
    return remember(configuration.screenWidthDp, configuration.screenHeightDp) {
        @Suppress("DEPRECATION")
        val mode = WindowSizeClass.compute(
            dpWidth = configuration.screenWidthDp.toFloat(),
            dpHeight = configuration.screenHeightDp.toFloat()
        ).toLayoutMode()
        AdaptiveLayoutState(mode, FoldPosture.Flat)
    }
}
