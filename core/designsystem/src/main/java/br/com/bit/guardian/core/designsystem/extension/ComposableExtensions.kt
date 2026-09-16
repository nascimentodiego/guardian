package br.com.bit.guardian.core.designsystem.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.adaptive.AdaptiveLayoutState
import br.com.bit.guardian.core.designsystem.adaptive.LayoutMode

@Composable
inline fun AdaptiveLayoutState.handleScreenBySize(
    compactScreen: @Composable () -> Unit,
    expandedScreen: @Composable () -> Unit
) {
    if (mode == LayoutMode.Compact) {
        compactScreen.invoke()
    } else {
        expandedScreen.invoke()
    }
}

@Composable
fun Modifier.conditional(
    condition: Boolean,
    modifier: @Composable Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        this.modifier()
    } else {
        this
    }
}
