package br.com.bit.guardian.login.ui.composable

import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass
import br.com.bit.guardian.login.ui.composable.compact.LoginCompactScreen
import br.com.bit.guardian.login.ui.composable.expanded.LoginExpandedScreen
import br.com.bit.guardian.login.ui.model.UserLoginUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: UserLoginUiState?,
    windowSizeClass: WindowSizeClass? = null
) {
    val widthSize = windowSizeClass?.widthSizeClass ?: LocalWindowSizeClass.current.widthSizeClass
    val heightSize = windowSizeClass?.widthSizeClass ?: LocalWindowSizeClass.current.heightSizeClass

    val isWidthCompact = widthSize == WindowWidthSizeClass.Compact
    val isHeightExpanded = heightSize == WindowHeightSizeClass.Expanded

    if (isWidthCompact || isHeightExpanded) {
        LoginCompactScreen(modifier,uiState)
    } else {
        LoginExpandedScreen(modifier,uiState)
    }
}

@Composable
@Preview
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: UserLoginUiState
) {
    GuardianTheme {
        LoginScreen(uiState = uiState)
    }
}