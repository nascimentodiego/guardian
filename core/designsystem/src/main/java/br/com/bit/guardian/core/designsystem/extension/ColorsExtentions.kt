package br.com.bit.guardian.core.designsystem.extension

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun OutlinedTextFieldDefaults.forceWhite() = colors(
    focusedBorderColor = Color.White,
    focusedLabelColor = Color.White,
    focusedTextColor = Color.White,
    unfocusedLabelColor = Color.White,
    unfocusedTextColor = Color.White.copy(alpha = 0.9f),
    cursorColor = Color.White,
    errorBorderColor = GuardianTheme.colors.error,
    errorCursorColor = GuardianTheme.colors.error,
    errorTextColor = GuardianTheme.colors.error,
    errorLabelColor = GuardianTheme.colors.error
)

@Composable
fun OutlinedTextFieldDefaults.guardianTextColor() = colors(
    focusedBorderColor = GuardianTheme.colors.textTitle,
    focusedLabelColor = GuardianTheme.colors.textTitle,
    focusedTextColor = GuardianTheme.colors.textTitle,
    unfocusedLabelColor = GuardianTheme.colors.textTitle,
    unfocusedTextColor = GuardianTheme.colors.textTitle,
    cursorColor = GuardianTheme.colors.textTitle,

    errorBorderColor = GuardianTheme.colors.error,
    errorCursorColor = GuardianTheme.colors.error,
    errorTextColor = GuardianTheme.colors.error,
    errorLabelColor = GuardianTheme.colors.error
)

@Composable
fun OutlinedTextFieldDefaults.onBackgroundColor() = colors(
    focusedBorderColor = GuardianTheme.colors.onBackground,
    focusedLabelColor = GuardianTheme.colors.onBackground,
    focusedTextColor = GuardianTheme.colors.onBackground,
    unfocusedLabelColor = GuardianTheme.colors.onBackground,
    cursorColor = GuardianTheme.colors.onBackground,

    errorBorderColor = GuardianTheme.colors.error,
    errorCursorColor = GuardianTheme.colors.error,
    errorTextColor = GuardianTheme.colors.error,
    errorLabelColor = GuardianTheme.colors.error
)


@Composable
fun Color.withState(enabled: Boolean): Color = if (enabled) this else this.copy(alpha = 0.2f)
