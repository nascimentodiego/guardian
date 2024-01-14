package br.com.bit.guardian.core.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.bit.guardian.core.designsystem.R

val Lexend = FontFamily(
    Font(R.font.lexend_regular),
    Font(R.font.lexend_thin, FontWeight.W300),
    Font(R.font.lexend_medium, FontWeight.W500),
    Font(R.font.lexend_semi_bold, FontWeight.W600),
    Font(R.font.lexend_bold, FontWeight.W700)
)

val Cabin = FontFamily(
    Font(R.font.cabin_bold, FontWeight.W700)
)

val Typography = Typography(
    //body
    bodyLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W300,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 8.sp,
        lineHeight = 19.sp
    ),
    // Label
    labelLarge = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // Title
    titleMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 29.5.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 29.5.sp,
        letterSpacing = 0.5.sp
    ),
    // Display
    displayMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    )
)

internal val LocalAppTypography = staticCompositionLocalOf {
    Typography
}
