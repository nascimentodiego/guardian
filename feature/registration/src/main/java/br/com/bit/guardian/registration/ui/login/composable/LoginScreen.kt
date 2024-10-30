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
import br.com.bit.guardian.registration.ui.login.model.LoginEvent
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState?,
    intent: (events: LoginIntent) -> Unit,
    callbacks: LoginListener
) {
    LocalWindowSizeClass.current.handleScreenBySize(
        compactScreen = { LoginCompactScreen(modifier, uiState, intent, callbacks) },
        expandedScreen = { LoginExpandedScreen(modifier, uiState, intent, callbacks) }
    )
}

@Composable
@Preview
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: LoginUiState
) {
    GuardianTheme {
        LoginScreen(uiState = uiState,
            intent = {},
            callbacks = object : LoginListener {
                override fun onCreateUserClickListener() {}
            })
    }
}