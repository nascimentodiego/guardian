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

    // bodySmall (Paragraph Small)
    bodySmall = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W400,
        fontSize = 12.sp,
        lineHeight = 16.8.sp
    ),

    // bodyMedium (Paragraph)
    bodyMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
        lineHeight = 19.6.sp
    ),

    // headlineMedium (Paragraph Bold)
    headlineMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W700,
        fontSize = 14.sp,
        lineHeight = 19.6.sp
    ),

    // Label (Subtitle)
    labelMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        lineHeight = 20.sp
    ),

    // Title Small
    titleSmall = TextStyle(
        fontFamily = Cabin,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        lineHeight = 22.4.sp
    ),

    // Title
    titleMedium = TextStyle(
        fontFamily = Lexend,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
        lineHeight = 33.6.sp
    )
)

internal val LocalAppTypography = staticCompositionLocalOf {
    Typography
}
