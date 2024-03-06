package br.com.bit.guardian.core.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


internal val Purple80 = Color(0xFFD0BCFF)
internal val PurpleGrey80 = Color(0xFFCCC2DC)
internal val Pink80 = Color(0xFFEFB8C8)


internal val PurpleGrey40 = Color(0xFF625b71)
internal val Pink40 = Color(0xFF7D5260)


internal val Red200 = Color(0xfff297a2)
internal val Red300 = Color(0xffea6d7e)
internal val Red700 = Color(0xffdd0d3c)
internal val Red800 = Color(0xffd00036)
internal val Red900 = Color(0xffc20029)

internal val Green900 = Color(0xFF114400)
internal val Green800 = Color(0xFF0E5900)
internal val Green700 = Color(0xFF166E00)
internal val Green600 = Color(0xFF2C8400)
internal val Green500 = Color(0xFF489900)
internal val Green400 = Color(0xFF67AD00)
internal val Green300 = Color(0xFF89C142)
internal val Green200 = Color(0xFFABD375)
internal val Green100 = Color(0xFFCDE5A9)
internal val Green50 = Color(0xFFEFF7E2)

internal val Gray900 = Color(0xFF3A3939)
internal val Gray800 = Color(0xFF4C4B4B)
internal val Gray700 = Color(0xFF5F5E5E)
internal val Gray600 = Color(0xFF737272)
internal val Gray500 = Color(0xFF878686)
internal val Gray400 = Color(0xFFC6C6C6)
internal val Gray300 = Color(0xFFB1B0B0)
internal val Gray200 = Color(0xFFC7C6C6)
internal val Gray100 = Color(0xFFDDDDDD)

internal val GreenBold = Color(0xFF5A9107)
internal val Transparent = Color(0x00FFFFFF)

internal val Black = Color(0xFF1A1A1A) // Background Dark
internal val White = Color(0xFFFCFCFC) // Background Light
internal val GreenContent_50 = Color(0xFFF0F6E6)


// Colors Light
internal val Purple40 = Color(0xFF452890) // Primary Light
internal val Gray50 = Color(0xFFF5F5F5) // OnPrimary Light
internal val Purple100 = Color(0xFF6650a4) // Primary Container Light
internal val Purple900 = Color(0xFFe8ddff) // OnPrimary Container Light

internal val Gray = Color(0xFF585757) // Secondary Light
internal val Gray02 = Color(0xFFb3b3b3) // OnSecondary Light

internal val Orange01 = Color(0xFFfe6c3b) // Tertiary Light
internal val Gray04 = Color(0xFFF5F5F5) // OnTertiary Light

internal val GrayLight01 = Color(0xFFffffff) // Surface Light
internal val GrayLight02 = Color(0xFFddd7e4) // Background Light
internal val GrayLight03 = Color(0xFF343435) // OnBackground Light


internal val LightColorSuccess = Color(0xFF27AE60)
internal val LightColorWarning = Color(0xFFE2B93B)
internal val LightColorError = Color(0xFFEB5757)
internal val LightColorDisable = Gray400


internal val TextTitleLight = Color(0xFF000000)
internal val TextSubtitleLight = Color(0xE6000000)
internal val TextBodyLight = Color(0xCC000000)
internal val TextDisableLight = Color(0x73000000)
internal val TextInverseLight = Color(0xFFFFFFFF)

// Colors Dark
internal val Purple40Dark = Purple40 // Primary Dark
internal val Gray50Dark = Color(0xFFF5F5F5) // OnPrimary Dark
internal val Purple100Dark = Color(0xFF6650a4) // Primary Container Dark
internal val Purple900Dark = Color(0xFFe8ddff) // OnPrimary Container Dark

internal val GrayDark = Color(0xFF474545) // Secondary Dark
internal val Gray02Dark = Color(0xFFCEC8C8) // OnSecondary Dark

internal val Orange01Dark = Color(0xFFfe6c3b) // Tertiary Dark
internal val Gray04Dark = Color(0xFFF5F5F5) // OnTertiary Dark

internal val GrayLight01Dark = Black // Surface Dark
internal val GrayLight02Dark = Color(0xFF39383A) // Background Dark
internal val GrayLight03Dark = Color(0xFFE3E3E6) // OnBackground Dark

internal val TextTitleDark = Color(0xFFFFFFFF)
internal val TextSubtitleDark = Color(0xE6FFFFFF)
internal val TextBodyDark = Color(0xCCFFFFFF)
internal val TextDisableDark = Color(0x73FFFFFF)
internal val TextInverseDark = Color(0xFF242424)

val GuardianLightColorScheme = lightColorScheme(
    primary = Purple40,
    onPrimary = White,
    primaryContainer = Purple100,
    onPrimaryContainer = Purple900,

    secondary = Gray,
    onSecondary = Gray02,

    tertiary = Orange01,
    onTertiary = Gray04,

    error = LightColorError,
    onError = White,
    errorContainer = Red300,
    onErrorContainer = Red200,

    background = GrayLight02,
    onBackground = Black,

    surface = GrayLight01,
    onSurface = Gray,
    outline = LightColorDisable
)

val GuardianDarkColorScheme = darkColorScheme(
    primary = Purple40Dark,
    onPrimary = Gray50Dark,
    primaryContainer = Purple100Dark,
    onPrimaryContainer = Purple900Dark,

    secondary = GrayDark,
    onSecondary = Gray02Dark,

    tertiary = Orange01Dark,
    onTertiary = Gray04Dark,

    error = LightColorError,
    onError = White,
    errorContainer = Red300,
    onErrorContainer = Red200,

    background = GrayLight02Dark,
    onBackground = White,

    surface = GrayLight01Dark,
    onSurface = GrayDark,
    outline = LightColorDisable
)

internal fun getColorScheme(darkTheme: Boolean) = AppColors(
    warning = if (darkTheme) LightColorWarning else LightColorWarning,
    success = if (darkTheme) LightColorSuccess else LightColorSuccess,
    disable = if (darkTheme) LightColorDisable else LightColorDisable,
    textTitle = if (darkTheme) TextTitleDark else TextTitleLight,
    textSubtitle = if (darkTheme) TextSubtitleDark else TextSubtitleLight,
    textBody = if (darkTheme) TextBodyDark else TextBodyLight,
    textDisable = if (darkTheme) TextDisableDark else TextDisableLight,
    textInverse = if (darkTheme) TextInverseDark else TextInverseLight,

    isLight = !darkTheme,
    materialColors = if (darkTheme) GuardianDarkColorScheme else GuardianLightColorScheme
)

@Stable
class AppColors(
    val warning: Color,
    val success: Color,
    val disable: Color,
    val textTitle: Color,
    val textSubtitle: Color,
    val textBody: Color,
    val textDisable: Color,
    val textInverse: Color,

    val isLight: Boolean,
    val materialColors: ColorScheme
) {
    val primary: Color
        get() = materialColors.primary
    val onPrimary: Color
        get() = materialColors.onPrimary
    val primaryContainer: Color
        get() = materialColors.primaryContainer
    val onPrimaryContainer: Color
        get() = materialColors.onPrimaryContainer

    val secondary: Color
        get() = materialColors.secondary
    val onSecondary: Color
        get() = materialColors.onSecondary

    val tertiary: Color
        get() = materialColors.tertiary
    val onTertiary: Color
        get() = materialColors.onTertiary

    val surface: Color
        get() = materialColors.surface
    val onSurface: Color
        get() = materialColors.onSurface

    val error: Color
        get() = materialColors.error
    val onError: Color
        get() = materialColors.onError

    val background: Color
        get() = materialColors.background

    val onBackground: Color
        get() = materialColors.onBackground

    val outline: Color
        get() = materialColors.outline
}

internal val LocalAppColors = compositionLocalOf {
    defaultAppColors()
}

fun defaultAppColors() = getColorScheme(false)
