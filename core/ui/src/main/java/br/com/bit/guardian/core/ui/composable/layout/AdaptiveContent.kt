package br.com.bit.guardian.core.ui.composable.layout

import androidx.compose.runtime.Composable
import br.com.bit.guardian.core.designsystem.adaptive.FoldPosture
import br.com.bit.guardian.core.designsystem.adaptive.LayoutMode
import br.com.bit.guardian.core.designsystem.adaptive.rememberAdaptiveLayoutState

@Composable
fun AdaptiveContent(
    expandedContent: @Composable () -> Unit = {},
    compactContent: @Composable () -> Unit = {}
) {
    val layout = rememberAdaptiveLayoutState()
    if (layout.mode != LayoutMode.Compact || layout.posture == FoldPosture.Tabletop) {
        expandedContent()
    } else {
        compactContent()
    }
}
