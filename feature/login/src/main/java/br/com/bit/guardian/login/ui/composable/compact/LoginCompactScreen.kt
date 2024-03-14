package br.com.bit.guardian.login.ui.composable.compact

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.backgroundGradientPrimary
import br.com.bit.guardian.login.ui.composable.LoginScreenProvider
import br.com.bit.guardian.login.ui.model.UserLoginUiState

@Composable
fun LoginCompactScreen(modifier: Modifier = Modifier, uiState: UserLoginUiState?) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .backgroundGradientPrimary(),
        contentAlignment = Alignment.Center
    ) {
        uiState?.let {
            when (uiState) {
                is UserLoginUiState.Loading -> {}
                is UserLoginUiState.Success -> LoginCompactSuccess(uiState = it)
                is UserLoginUiState.Error -> {}
            }
        }
    }
}



@ThemePreviews
@Composable
fun LoginCompactScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: UserLoginUiState
) {
    GuardianTheme {
        LoginCompactScreen(uiState = uiState)
    }
}