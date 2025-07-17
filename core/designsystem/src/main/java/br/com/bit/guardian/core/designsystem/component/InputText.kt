package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import br.com.bit.guardian.core.designsystem.extension.guardianTextColor

@Composable
fun OutlinedInputText(
    modifier: Modifier = Modifier,
    text: String,
    isError: Boolean,
    colors: TextFieldColors = OutlinedTextFieldDefaults.guardianTextColor(),
    @StringRes label: Int,
    @StringRes supportingText: Int,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        maxLines = 1,
        singleLine = true,
        label = { Text(stringResource(id = label)) },
        isError = isError,
        supportingText = {
            if (isError)
                Text(stringResource(id = supportingText))
        },
        colors = colors
    )
}