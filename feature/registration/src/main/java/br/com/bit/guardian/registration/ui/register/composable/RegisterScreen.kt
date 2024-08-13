package br.com.bit.guardian.registration.ui.register.composable

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.extension.handleScreenBySize
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass
import br.com.bit.guardian.registration.ui.login.composable.LoginScreenProvider
import br.com.bit.guardian.registration.ui.login.model.UserLoginUiState
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    uiState: RegisterUiState?
) {
    LocalWindowSizeClass.current.handleScreenBySize(
        compactScreen = {
           Text(text = "Compact")
        },
        expandedScreen = {
            Text(text = "Expanded")
        }
    )
}

@Composable
@Preview
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: UserLoginUiState
) {
    GuardianTheme {
//        RegisterScreen(uiState = uiState)
    }
}