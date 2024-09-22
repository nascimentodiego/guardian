package br.com.bit.guardian.registration.ui.login.composable.expanded

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.GuardianDisplayMedium
import br.com.bit.guardian.core.designsystem.component.GuardianLogoSmall
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.extension.guardianTextColor
import br.com.bit.guardian.core.designsystem.extension.isHeightCompact
import br.com.bit.guardian.core.designsystem.extension.onBackgroundColor
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.model.LoginIntent
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.register.composable.components.OutlinedInputEmailText
import br.com.bit.guardian.registration.ui.register.composable.components.OutlinedPasswordText

@Composable
fun LoginExpandedSuccess(
    uiState: LoginUiState,
    intent: (events: LoginIntent) -> Unit,
    callbacks: LoginListener
) {
    BoxWithConstraints {
        val isCompact = isHeightCompact()
        val scroll = rememberScrollState()
        var passwordVisible by rememberSaveable { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(if (!isCompact) GuardianTheme.dimens.spacingM else 0.dp)
                .verticalScroll(scroll),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isCompact) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    GuardianLogoSmall(
                        tintColor = GuardianTheme.colors.onBackground,
                        iconSize = 72.dp
                    )
                    GuardianDisplayMedium(color = GuardianTheme.colors.onBackground)
                }

            } else {
                GuardianLogoSmall(tintColor = GuardianTheme.colors.onBackground)
                GuardianDisplayMedium(color = GuardianTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))

            OutlinedInputEmailText(
                modifier = Modifier.fillMaxWidth(),
                email = uiState.userView.email,
                isError = false,
                colors = OutlinedTextFieldDefaults.onBackgroundColor(),
                putEmail = {
                    intent(LoginIntent.InputEmail(it))
                },
            )
            OutlinedPasswordText(
                modifier = Modifier.fillMaxWidth(),
                password = uiState.userView.password,
                passwordVisible = passwordVisible,
                label = R.string.login_input_title_password,
                supportingText = R.string.login_register_invalid_password,
                isError = false,
                colors = OutlinedTextFieldDefaults.guardianTextColor(),
                iconColor = GuardianTheme.colors.textTitle,
                visibilityClick = {
                    passwordVisible = !passwordVisible
                }
            ) {
                intent(LoginIntent.InputPassword(it))
            }

            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
            if (isCompact) {
                Row {
                    LoadedTertiaryButton(
                        enabled = uiState.userView.isButtonEnabled,
                        isLoading = uiState.userView.isButtonLoading,
                        onClick = {  intent(LoginIntent.Login) },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_btn_login),
                            modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                            color = GuardianTheme.colors.onBackground.withState(enabled = true)
                        )
                    }
                    Spacer(
                        modifier = Modifier.weight(0.1f)
                    )
                    OutlinedButton(
                        modifier = Modifier.weight(1f),
                        onClick = { callbacks.onCreateUserClickListener() }
                    ) {
                        Text(
                            text = stringResource(id = R.string.login_btn_create),
                            modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                            color = GuardianTheme.colors.onBackground
                        )
                    }

                }
            } else {
                LoadedTertiaryButton(
                    enabled = uiState.userView.isButtonEnabled,
                    isLoading =  uiState.userView.isButtonLoading,
                    onClick = {  intent(LoginIntent.Login)  },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.login_btn_login),
                        modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                        color = GuardianTheme.colors.onBackground.withState(enabled = true)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        color = GuardianTheme.colors.onBackground
                    )
                    Text(
                        text = stringResource(id = R.string.login_input_title_or),
                        modifier = Modifier.padding(GuardianTheme.dimens.spacingXS),
                        style = GuardianTheme.typography.bodyMedium,
                        color = GuardianTheme.colors.onBackground
                    )
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .weight(1f),
                        color = GuardianTheme.colors.onBackground
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
                        color = GuardianTheme.colors.onBackground
                    )
                }
            }
        }
    }
}