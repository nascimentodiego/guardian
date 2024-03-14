package br.com.bit.guardian.login.ui.composable.expanded

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
import br.com.bit.guardian.login.ui.composable.LoginScreenProvider
import br.com.bit.guardian.login.ui.model.UserLoginUiState

@Composable
fun LoginExpandedScreen(modifier: Modifier = Modifier, uiState: UserLoginUiState?) {
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
                    .backgroundGradientPrimary(), //RoundedCornerShape(topEnd = 72.dp, bottomEnd = 72.dp)
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
                        is UserLoginUiState.Loading -> {}
                        is UserLoginUiState.Success -> {
                            LoginExpandedSuccess(uiState = uiState)
                        }

                        is UserLoginUiState.Error -> {}
                    }
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, device = Devices.TABLET)
@Composable
fun LoginExpandedScreenPreview(
    @PreviewParameter(LoginScreenProvider::class) uiState: UserLoginUiState
) {
    GuardianTheme {
        LoginExpandedScreen(uiState = uiState)
    }
}
