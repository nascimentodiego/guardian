package br.com.bit.guardian.core.designsystem.adaptive

import androidx.compose.material3.adaptive.Posture
import androidx.compose.material3.adaptive.WindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.window.core.layout.WindowSizeClass

/**
 * Layout modes describe the available WINDOW, never the device. A landscape
 * phone and a short multi-window tablet are the same case and get the same
 * layout.
 */
enum class LayoutMode {
    /** Narrow width: single column, bottom navigation. */
    Compact,

    /** Wide but short height (landscape phone, low multi-window). */
    WideShort,

    /** Wide and tall (open foldable, tablet, desktop). */
    WideTall
}

/**
 * Fold posture. Orthogonal to [LayoutMode]: an open foldable and a tablet
 * share the same mode and base layout — only the posture differs.
 */
enum class FoldPosture {
    /** No relevant fold: flat screen. */
    Flat,

    /** Horizontal hinge splitting the screen: top half and bottom half. */
    Tabletop,

    /** Vertical hinge splitting the screen: two natural columns (book). */
    Book
}

@Immutable
data class AdaptiveLayoutState(
    val mode: LayoutMode,
    val posture: FoldPosture
)

/**
 * Width uses the MEDIUM breakpoint (600dp), matching Guardian's existing
 * compact/expanded split, so small tablets and split-screen windows keep
 * getting the expanded treatment. Height uses the MEDIUM breakpoint (480dp)
 * to separate a landscape phone (wide, short) from a tablet/foldable (wide,
 * tall).
 */
fun WindowSizeClass.toLayoutMode(): LayoutMode {
    val isWide = isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_MEDIUM_LOWER_BOUND)
    val isTall = isHeightAtLeastBreakpoint(WindowSizeClass.HEIGHT_DP_MEDIUM_LOWER_BOUND)

    return when {
        isWide && isTall -> LayoutMode.WideTall
        isWide -> LayoutMode.WideShort
        else -> LayoutMode.Compact
    }
}

/**
 * Only a hinge that actually separates the content and isn't flat counts —
 * a fully open foldable reports its hinge, but it separates nothing.
 */
fun Posture.toFoldPosture(): FoldPosture {
    val separating = hingeList.firstOrNull { it.isSeparating && !it.isFlat }
        ?: return FoldPosture.Flat

    return if (separating.isVertical) FoldPosture.Book else FoldPosture.Tabletop
}

/**
 * Uses [currentWindowAdaptiveInfo] rather than its V2 successor: the V2 entry
 * point ships in `adaptive-android` 1.3.0+, which requires compileSdk 37 and
 * AGP 9.1 — well beyond this project's current toolchain. Revisit once the
 * project upgrades.
 */
@Composable
fun rememberAdaptiveLayoutState(
    info: WindowAdaptiveInfo = currentWindowAdaptiveInfo()
): AdaptiveLayoutState = AdaptiveLayoutState(
    mode = info.windowSizeClass.toLayoutMode(),
    posture = info.windowPosture.toFoldPosture()
)

val LocalAdaptiveLayout = compositionLocalOf {
    AdaptiveLayoutState(LayoutMode.Compact, FoldPosture.Flat)
}
