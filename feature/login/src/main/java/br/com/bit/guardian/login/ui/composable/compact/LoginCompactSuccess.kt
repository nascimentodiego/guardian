package br.com.bit.guardian.login.ui.composable.compact

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.GuardianLogoSmall
import br.com.bit.guardian.core.designsystem.component.GuardianTitleLarge
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.extension.forceWhite
import br.com.bit.guardian.core.designsystem.extension.isWidthMedium
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.login.R
import br.com.bit.guardian.login.ui.model.UserLoginUiState

@Composable
fun LoginCompactSuccess(uiState: UserLoginUiState?) {
    BoxWithConstraints {
        val mainPadding =
            if (isWidthMedium()) GuardianTheme.dimens.spacingHX else GuardianTheme.dimens.spacingM

        Column(
            modifier = Modifier
                .padding(mainPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GuardianLogoSmall(tintColor = Color.White)
            GuardianTitleLarge(color = Color.White)
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(id = R.string.login_input_title_email)) },
                isError = false,
                supportingText = {
//                if(true)
//                Text(text = "email inválido !")
                },
                colors = OutlinedTextFieldDefaults.forceWhite()
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(stringResource(id = R.string.login_input_title_password)) },
                supportingText = { },
                colors = OutlinedTextFieldDefaults.forceWhite()
            )
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
            LoadedTertiaryButton(
                enabled = true,
                isLoading = false,
                onClick = { /*TODO*/ },
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
                onClick = { /*TODO*/ }
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