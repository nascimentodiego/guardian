package br.com.bit.guardian.core.designsystem.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme.colors

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

    CompositionLocalProvider(
        LocalAppColors provides colorScheme,
        LocalAppDimens provides AppDims,
        LocalAppTypography provides Typography
    ) {
        MaterialTheme(
            colorScheme = colorScheme.materialColors,
            typography = Typography,
            shapes = GuardianShapes,
            content = content
        )
    }
}