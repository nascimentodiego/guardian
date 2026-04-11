package br.com.bit.guardian.registration.ui.register.composable.expanded

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.register.composable.RegistrationRules
import br.com.bit.guardian.registration.ui.register.composable.components.InputEmail
import br.com.bit.guardian.registration.ui.register.composable.components.PasswordTextField
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationIntent

@Composable
fun RegisterExpandedScreen(
    state: RegisterUiState,
    intent: (RegistrationIntent) -> Unit,
    passwordVisible: Boolean = false,
    visibilityClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(GuardianTheme.dimens.spacingXS),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top
    ) {
        Box(modifier = Modifier.weight(1f)) {
            Column {
                InputEmail(
                    state,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    intent.invoke(RegistrationIntent.InputEmail(it))
                }
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    password = state.ruleState.password,
                    passwordVisible = passwordVisible,
                    label = R.string.login_input_title_password,
                    supportingText = R.string.login_register_invalid_password,
                    isError = state.ruleState.invalidPass,
                    visibilityClick = {
                        visibilityClick.invoke()
                    }
                ) {
                    intent.invoke(RegistrationIntent.InputPassword(it))
                }
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
                PasswordTextField(
                    modifier = Modifier.fillMaxWidth(),
                    password = state.ruleState.passwordConfirm,
                    passwordVisible = passwordVisible,
                    label = R.string.login_input_title_conf_password,
                    supportingText = R.string.login_input_title_conf_password,
                    isError = state.ruleState.invalidConfPass,
                    visibilityClick = {
                        visibilityClick.invoke()
                    }
                ) {
                    intent.invoke(RegistrationIntent.ConfirmPassword(it))
                }
            }
        }
        Divider(modifier = Modifier.width(GuardianTheme.dimens.spacingH))
        Box(modifier = Modifier.weight(1f)) {
            Column {
                RegistrationRules(state.ruleState.listOfPassError)
            }
        }
    }
}