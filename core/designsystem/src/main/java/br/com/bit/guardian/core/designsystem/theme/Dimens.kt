package br.com.bit.guardian.core.designsystem.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
object AppDims {
    val spacingHX: Dp = 52.dp
    val spacingH: Dp = 48.dp
    val spacingXXL: Dp = 32.dp
    val spacingXL: Dp = 24.dp
    val spacingL: Dp = 20.dp
    val spacingM: Dp = 16.dp
    val spacingS: Dp = 12.dp
    val spacingXS: Dp = 8.dp
    val spacingXXS: Dp = 4.dp
}

internal val LocalAppDimens = staticCompositionLocalOf {
    AppDims
}
