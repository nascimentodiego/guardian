@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package br.com.bit.guardian.settings.ui.profile.composable.components.bottomsheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.core.designsystem.component.LoadedButton
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.component.OutlinedInputText
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.modifier.navigationBarPaddingOnly
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.settings.R
import br.com.bit.guardian.settings.ui.profile.model.NickName
import br.com.bit.guardian.core.designsystem.R as Rds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun NickNameComposable(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean = false,
    nickName: NickName,
    sheetState: SheetState = rememberModalBottomSheetState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onTextChange: (String) -> Unit
) {
    val cancelEvent = remember {
        {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onDismissRequest.invoke()
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { cancelEvent.invoke() },
            sheetState = sheetState
        ) {
            NickNameContent(
                modifier = modifier,
                nickname = nickName.newValue,
                isError = nickName.isError,
                isLoading = nickName.isLoading,
                onNicknameChange = onTextChange,
                onCancel = { cancelEvent.invoke() },
                onConfirm = onConfirm
            )
        }
    }
}

@Composable
internal fun NickNameContent(
    modifier: Modifier = Modifier,
    nickname: String,
    isLoading: Boolean = false,
    isError: Boolean = false,
    onNicknameChange: (String) -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Column(modifier = modifier.padding(GuardianTheme.dimens.spacingM).navigationBarPaddingOnly()) {
        OutlinedInputText(
            modifier = modifier
                .fillMaxWidth(),
            text = nickname,
            isError = isError,
            label = R.string.settings_nickname_label,
            supportingText = R.string.settings_nickname_invalid_input,
            onValueChange = onNicknameChange
        )
        Spacer(modifier = modifier.height(GuardianTheme.dimens.spacingS))
        Row(modifier = Modifier) {
            LoadedTertiaryButton(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = Rds.string.ds_button_cancel),
                    color = Color.White.withState(enabled = true)
                )
            }
            Spacer(modifier = modifier.width(GuardianTheme.dimens.spacingXXS))
            LoadedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = onConfirm,
                isLoading = isLoading
            ) {
                Text(
                    text = stringResource(id = Rds.string.ds_button_confirm),
                    color = Color.White.withState(enabled = true)
                )
            }
        }
    }
}

@Preview
@Composable
fun NicknameContentPreview() {
    GuardianTheme {
        Surface {
            NickNameContent(
                onNicknameChange = {},
                onCancel = {},
                onConfirm = { },
                nickname = ""
            )
        }
    }
}