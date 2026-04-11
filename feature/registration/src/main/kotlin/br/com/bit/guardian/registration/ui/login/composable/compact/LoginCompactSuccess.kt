package br.com.bit.guardian.registration.ui.login.composable.compact

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.GuardianLogoMedium
import br.com.bit.guardian.core.designsystem.component.GuardianTitleLarge
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.extension.isWidthMedium
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.register.composable.components.OutlinedInputEmailText
import br.com.bit.guardian.registration.ui.register.composable.components.OutlinedPasswordText

@Composable
fun LoginCompactSuccess(
    uiState: LoginUiState,
    intent: (events: LoginIntent) -> Unit,
    callbacks: LoginListener
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    BoxWithConstraints {
        val mainPadding =
            if (isWidthMedium()) GuardianTheme.dimens.spacingHX else GuardianTheme.dimens.spacingM

        Column(
            modifier = Modifier
                .padding(mainPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GuardianLogoMedium(tintColor = Color.White)
            GuardianTitleLarge(color = Color.White)
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXL))
            OutlinedInputEmailText(
                modifier = Modifier.fillMaxWidth(),
                email = uiState.userView.email,
                isError = false,
                putEmail = {
                    intent(LoginIntent.InputEmail(it))
                }
            )
            OutlinedPasswordText(
                modifier = Modifier.fillMaxWidth(),
                password = uiState.userView.password,
                passwordVisible = passwordVisible,
                label = R.string.login_input_title_password,
                supportingText = R.string.login_register_invalid_password,
                isError = false,
                visibilityClick = {
                    passwordVisible = !passwordVisible
                }
            ) {
                intent.invoke(LoginIntent.InputPassword(it))
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
            LoadedTertiaryButton(
                enabled = uiState.userView.isButtonEnabled,
                isLoading = uiState.userView.isButtonLoading,
                onClick = { intent(LoginIntent.Login) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.login_btn_login),
                    modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                    color = Color.White.withState(enabled = true)
                )
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    color = Color.White
                )
                Text(
                    text = stringResource(id = R.string.login_input_title_or),
                    modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                    style = GuardianTheme.typography.bodyMedium,
                    color = Color.White
                )
                Divider(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .weight(1f),
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { callbacks.onCreateUserClickListener() }
            ) {
                Text(
                    text = stringResource(id = R.string.login_btn_create),
                    modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                    color = Color.White
                )
            }
        }
    }
}