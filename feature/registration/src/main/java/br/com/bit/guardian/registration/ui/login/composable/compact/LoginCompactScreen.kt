package br.com.bit.guardian.registration.ui.login.composable.compact

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.backgroundGradientPrimary
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.composable.LoadingScreen
import br.com.bit.guardian.registration.ui.login.composable.LoginScreenProvider
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState

@Composable
fun LoginCompactScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState?,
    intent: (events: LoginIntent) -> Unit,
    callbacks: LoginListener
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .backgroundGradientPrimary(),
        contentAlignment = Alignment.Center
    ) {
        uiState?.let {
            when (uiState) {
                is LoginUiState.Loading -> LoadingScreen()
                is LoginUiState.Idle -> LoginCompactSuccess(uiState = uiState, intent, callbacks)
            }
        }
    }
}


@ThemePreviews
@Composable
fun LoginCompactScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: LoginUiState
) {
    GuardianTheme {
        LoginCompactScreen(uiState = uiState,
            intent = {},
            callbacks = object : LoginListener {
                override fun onCreateUserClickListener() {
                    TODO("Not yet implemented")
                }

                override fun onLoginClickListener(email: String, password: String) {
                    TODO("Not yet implemented")
                }
            })
    }
}