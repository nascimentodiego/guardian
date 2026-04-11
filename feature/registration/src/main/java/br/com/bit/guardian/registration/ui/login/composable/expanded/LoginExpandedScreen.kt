package br.com.bit.guardian.registration.ui.login.composable.expanded

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.backgroundGradientPrimary
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.composable.LoadingScreen
import br.com.bit.guardian.registration.ui.login.composable.LoginScreenProvider
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState

@Composable
fun LoginExpandedScreen(
    modifier: Modifier = Modifier,
    uiState: LoginUiState?,
    intent: (events: LoginIntent) -> Unit,
    callbacks: LoginListener
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(GuardianTheme.colors.background)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .backgroundGradientPrimary(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BitPlatformHighlight()
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(GuardianTheme.dimens.spacingXS)
                    .navigationBarsPadding(),
                contentAlignment = Alignment.Center
            ) {
                uiState?.let {
                    when (uiState) {
                        is LoginUiState.Loading -> LoadingScreen()
                        is LoginUiState.Idle -> {
                            LoginExpandedSuccess(uiState = uiState, intent, callbacks)
                        }
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.TABLET)
@Composable
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: LoginUiState
) {
    GuardianTheme {
        LoginExpandedScreen(
            uiState = uiState,
            intent = {},
            callbacks = object : LoginListener {
                override fun onCreateUserClickListener() {}
            }
        )
    }
}
