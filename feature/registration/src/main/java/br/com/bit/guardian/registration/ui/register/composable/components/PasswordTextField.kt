package br.com.bit.guardian.registration.ui.register.composable.components

import androidx.annotation.StringRes
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import br.com.bit.guardian.core.designsystem.extension.forceWhite
import br.com.bit.guardian.core.designsystem.extension.guardianTextColor
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.feature.registration.R
import br.com.bit.guardian.registration.ui.login.model.LoginIntent

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    passwordVisible: Boolean = false,
    @StringRes label: Int = R.string.login_input_title_password,
    @StringRes supportingText: Int?,
    isError: Boolean = false,
    visibilityClick: () -> Unit,
    onValueChange: (String) -> Unit
) = TextField(
    value = password,
    onValueChange = { onValueChange(it) },
    modifier = modifier,
    label = { Text(stringResource(id = label)) },
    isError = isError,
    maxLines = 1,
    supportingText = {
        supportingText?.let {
            if (isError)
                Text(text = stringResource(it))
        }
    },
    visualTransformation = if (passwordVisible)
        VisualTransformation.None
    else
        PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    trailingIcon = {
        val image = painterResource(
            if (passwordVisible) GuardianIcon.Visibility
            else GuardianIcon.VisibilityOff
        )
        val description = if (passwordVisible) "Hide password" else "Show password"

        IconButton(onClick = { visibilityClick.invoke() }) {
            Icon(painter = image, description)
        }
    },
    colors = OutlinedTextFieldDefaults.guardianTextColor()
)

@Composable
fun OutlinedPasswordText(
    modifier: Modifier = Modifier,
    password: String,
    passwordVisible: Boolean = false,
    @StringRes label: Int = R.string.login_input_title_password,
    @StringRes supportingText: Int?,
    isError: Boolean = false,
    colors: TextFieldColors = OutlinedTextFieldDefaults.forceWhite(),
    iconColor: Color = Color.White,
    visibilityClick: () -> Unit,
    onValueChange: (String) -> Unit
) = OutlinedTextField(
    value = password,
    onValueChange = { onValueChange(it) },
    modifier = modifier,
    label = { Text(stringResource(id = label)) },
    isError = isError,
    maxLines = 1,
    supportingText = {
        supportingText?.let {
            if (isError)
                Text(text = stringResource(it))
        }
    },
    visualTransformation = if (passwordVisible)
        VisualTransformation.None
    else
        PasswordVisualTransformation(),
    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
    trailingIcon = {
        val image = painterResource(
            if (passwordVisible) GuardianIcon.Visibility
            else GuardianIcon.VisibilityOff
        )
        val description = if (passwordVisible) "Hide password" else "Show password"

        IconButton(onClick = { visibilityClick.invoke() }) {
            Icon(painter = image, description, tint = iconColor)
        }
    },
    colors = colors
)

