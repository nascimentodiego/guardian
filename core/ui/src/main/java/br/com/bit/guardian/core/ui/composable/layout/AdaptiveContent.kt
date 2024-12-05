package br.com.bit.guardian.core.ui.composable.layout

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.compose.runtime.Composable

@Composable
fun AdaptiveContent(
    expandedContent: @Composable () -> Unit = {},
    compactContent: @Composable () -> Unit = {}
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    with(adaptiveInfo) {
        if (windowPosture.isTabletop ||
            windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED ||
            windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM
        ) {
            expandedContent()
        } else {
            compactContent()
        }
    }
}