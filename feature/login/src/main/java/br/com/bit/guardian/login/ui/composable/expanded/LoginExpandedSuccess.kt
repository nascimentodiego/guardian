package br.com.bit.guardian.login.ui.composable.expanded

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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.GuardianLogoSmall
import br.com.bit.guardian.core.designsystem.component.GuardianTitleLarge
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.extension.isHeightCompact
import br.com.bit.guardian.core.designsystem.extension.onBackgroundColor
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.login.R
import br.com.bit.guardian.login.ui.model.UserLoginUiState


@Composable
fun LoginExpandedSuccess(uiState: UserLoginUiState?) {
    BoxWithConstraints {
        val isCompact = isHeightCompact()
        val scroll = rememberScrollState()

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
                    GuardianTitleLarge(color = GuardianTheme.colors.onBackground)
                }

            } else {
                GuardianLogoSmall(tintColor = GuardianTheme.colors.onBackground)
                GuardianTitleLarge(color = GuardianTheme.colors.onBackground)
            }

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
                colors = OutlinedTextFieldDefaults.onBackgroundColor()
            )
            OutlinedTextField(
                value = "",
                onValueChange = { },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                label = { Text(stringResource(id = R.string.login_input_title_password)) },
                supportingText = { },
                colors = OutlinedTextFieldDefaults.onBackgroundColor()
            )
            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))

            Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
            if (isCompact) {
                Row {
                    LoadedTertiaryButton(
                        enabled = true,
                        isLoading = false,
                        onClick = { /*TODO*/ },
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
                        onClick = { /*TODO*/ }
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
                    enabled = true,
                    isLoading = false,
                    onClick = { /*TODO*/ },
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
                    onClick = { /*TODO*/ }
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