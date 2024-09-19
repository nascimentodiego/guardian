package br.com.bit.guardian.registration.ui.register.composable.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.bit.guardian.core.designsystem.extension.GuardianTextColor
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState

@Composable
fun InputEmail(
    state: RegisterUiState,
    modifier: Modifier = Modifier,
    putEmail: (String) -> Unit
) {
    TextField(
        value = state.ruleState.email,
        onValueChange = { putEmail(it) },
        modifier = modifier,
        label = { Text(stringResource(id = R.string.login_input_title_email)) },
        singleLine = true,
        isError = state.ruleState.invalidEmail,
        maxLines = 1,
        supportingText = {
            if (state.ruleState.invalidEmail)
                Text(text = stringResource(R.string.login_input_email_invalid))
        },
        colors = OutlinedTextFieldDefaults.GuardianTextColor()
    )
}