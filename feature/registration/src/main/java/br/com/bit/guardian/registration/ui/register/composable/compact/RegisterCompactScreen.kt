package br.com.bit.guardian.registration.ui.register.composable.compact

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.register.composable.RegistrationRules
import br.com.bit.guardian.registration.ui.register.composable.components.InputEmail
import br.com.bit.guardian.registration.ui.register.composable.components.PasswordTextField
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState

@Composable
fun RegisterCompactScreen(
    state: RegisterUiState,
    passwordVisible: Boolean = false,
    visibilityClick: () -> Unit,
    putEmail: (String) -> Unit,
    putPassword: (String) -> Unit,
    putConfPassword: (String) -> Unit
) {
    InputEmail(state, Modifier.fillMaxWidth(),putEmail)
    Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
    PasswordTextField(
        Modifier.fillMaxWidth(),
        password = state.ruleState.password,
        passwordVisible = passwordVisible,
        label = R.string.login_input_title_password,
        supportingText = R.string.login_register_invalid_password,
        isError = state.ruleState.invalidPass,
        visibilityClick = { visibilityClick.invoke() }
    ) {
        putPassword(it)
    }
    Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
    PasswordTextField(
        Modifier.fillMaxWidth(),
        password = state.ruleState.passwordConfirm,
        passwordVisible = passwordVisible,
        label = R.string.login_input_title_conf_password,
        supportingText = R.string.login_input_title_conf_password,
        isError = state.ruleState.invalidConfPass,
        visibilityClick = { visibilityClick.invoke() }
    ) {
        putConfPassword(it)
    }

    RegistrationRules(state.ruleState.listOfPassError)
}