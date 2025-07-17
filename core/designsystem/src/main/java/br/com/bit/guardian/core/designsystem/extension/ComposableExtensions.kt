package br.com.bit.guardian.core.designsystem.extension

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.GuardianWindowSize

@Composable
inline fun GuardianWindowSize.handleScreenBySize(
    compactScreen: () -> @Composable Unit,
    expandedScreen: () -> @Composable Unit
) {
    val widthSize = this.widthSizeClass
    val heightSize = this.heightSizeClass

    val isWidthCompact = widthSize == WindowWidthSizeClass.Compact
    val isHeightExpanded = heightSize == WindowHeightSizeClass.Expanded

    if (isWidthCompact || isHeightExpanded) {
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