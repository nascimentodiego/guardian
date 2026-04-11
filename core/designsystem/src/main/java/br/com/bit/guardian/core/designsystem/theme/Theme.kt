@file:OptIn(ExperimentalMaterial3WindowSizeClassApi::class)

package br.com.bit.guardian.core.designsystem.theme

import android.app.Activity
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.window.core.layout.WindowWidthSizeClass as WindowSize

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

@Composable
fun GuardianTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isStatusBarTranslucent: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = getColorScheme(darkTheme)
    val view = LocalView.current

    if (!isStatusBarTranslucent) {
        if (!view.isInEditMode) {
            SideEffect {
                val window = (view.context as Activity).window
                window.statusBarColor = colorScheme.primary.toArgb()
                WindowCompat.getInsetsController(
                    window,
                    view
                ).isAppearanceLightStatusBars = darkTheme
            }
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

    val adaptiveInfo = currentWindowAdaptiveInfo()
    val localAdaptiveContent = with(adaptiveInfo) {
        if (windowPosture.isTabletop ||
            windowSizeClass.windowWidthSizeClass == WindowSize.EXPANDED ||
            windowSizeClass.windowWidthSizeClass == WindowSize.MEDIUM
        ) {
            WindowWidthSizeClass.Expanded
        } else {
            WindowWidthSizeClass.Compact
        }
    }

    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppDimens provides AppDims,
        LocalAppTypography provides Typography,
        LocalWindowSizeClass provides guardianWindowSize,
        LocalAdaptiveContent provides localAdaptiveContent
    ) {
        MaterialTheme(
            colorScheme = colorScheme.materialColors,
            shapes = GuardianShapes,
            typography = Typography,
            content = content
        )
    }
}

@Composable
fun AdaptiveStatusBarStyle(
    background: Color = GuardianTheme.colors.surface
) {
    val window = (LocalActivity.current as Activity).window
    val iconsShouldBeDark = background.luminance() >= 0.5f

    LaunchedEffect(background) {
        WindowCompat.getInsetsController(
            window,
            window.decorView
        ).isAppearanceLightStatusBars = iconsShouldBeDark // true = ícones escuros
    }
}
