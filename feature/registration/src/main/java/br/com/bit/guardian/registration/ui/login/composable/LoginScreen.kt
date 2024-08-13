package br.com.bit.guardian.registration.ui.login.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.extension.handleScreenBySize
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.composable.compact.LoginCompactScreen
import br.com.bit.guardian.registration.ui.login.composable.expanded.LoginExpandedScreen
import br.com.bit.guardian.registration.ui.login.model.UserLoginUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: UserLoginUiState?,
    callbacks: LoginListener
) {
    LocalWindowSizeClass.current.handleScreenBySize(
        compactScreen = { LoginCompactScreen(modifier, uiState, callbacks) },
        expandedScreen = { LoginExpandedScreen(modifier, uiState, callbacks) }
    )
}

@Composable
@Preview
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: UserLoginUiState
) {
    GuardianTheme {
        LoginScreen(uiState = uiState, callbacks = object : LoginListener {
            override fun onCreateUserClickListener() {
                TODO("Not yet implemented")
            }

            override fun onLoginClickListener(email: String, password: String) {
                TODO("Not yet implemented")
            }
        })
    }
}