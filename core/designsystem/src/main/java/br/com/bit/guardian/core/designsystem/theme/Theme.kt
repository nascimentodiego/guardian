package br.com.bit.guardian.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

object GuardianTheme {
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val dimens: AppDims
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimens.current

    val typography: Typography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun GuardianTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(darkTheme)

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }
    val guardianWindowSize = if (!view.isInEditMode) {
        val windowWidthSizeClass = calculateWindowSizeClass((view.context as Activity))
        GuardianWindowSize(
            windowWidthSizeClass.widthSizeClass,
            windowWidthSizeClass.heightSizeClass
        )
    } else {
        GuardianWindowSize.Compact
    }

    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppDimens provides AppDims,
        LocalAppTypography provides Typography,
        LocalWindowSizeClass provides guardianWindowSize
    ) {
        MaterialTheme(
            colorScheme = colorScheme.materialColors,
            typography = Typography,
            shapes = GuardianShapes,
            content = content
        )
    }
}

