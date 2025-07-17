@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.bit.guardian.settings.ui.profile.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextBodyMedium
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.settings.R
import br.com.bit.guardian.settings.ui.profile.composable.components.AvatarField
import br.com.bit.guardian.settings.ui.profile.composable.components.GenericField
import br.com.bit.guardian.settings.ui.profile.composable.components.NickNameField
import br.com.bit.guardian.settings.ui.profile.composable.components.bottomsheet.AvatarBottomSheet
import br.com.bit.guardian.settings.ui.profile.composable.components.bottomsheet.NickNameComposable
import br.com.bit.guardian.settings.ui.profile.model.SettingsUiState

@Composable
fun SettingsSuccess(
    modifier: Modifier = Modifier,
    uiState: SettingsUiState.Success,
    onEditAvatarClick: (avatarId: Int) -> Unit,
    onConfirmAvatar: (avatarId: Int) -> Unit,
    onEditNickNameClick: () -> Unit,
    onConfirmNickname: () -> Unit,
    onDismissRequest: () -> Unit,
    onNickNameTextChange: (String) -> Unit,
    onSignOutClick: () -> Unit,
    showNicknameBottomSheet: Boolean,
    showAvatarBottomSheet: Boolean,
    sheetState: SheetState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(GuardianTheme.dimens.spacingM)
            .verticalScroll(rememberScrollState())
    ) {
        // User Icon
        AvatarField(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            avatar = uiState.avatar.icon,
            onEditClick = { onEditAvatarClick.invoke(uiState.avatar.icon) }
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXL))
        AvatarBottomSheet(
            avatar = uiState.avatar,
            showBottomSheet = showAvatarBottomSheet,
            sheetState = sheetState,
            onDismissRequest = onDismissRequest,
            onConfirm = onConfirmAvatar
        )

        // Nickname field
        NickNameField(
            label = R.string.settings_nickname_label,
            value = uiState.nickname.text,
            onClick = onEditNickNameClick
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingH))
        NickNameComposable(
            showBottomSheet = showNicknameBottomSheet,
            sheetState = sheetState,
            nickName = uiState.nickname,
            onConfirm = onConfirmNickname,
            onDismissRequest = onDismissRequest,
            onTextChange = onNickNameTextChange
        )

        // Emergence phones field
        GenericField(
            label = R.string.settings_phone_title,
            onClick = { }
        )
        HorizontalDivider(color = GuardianTheme.colors.iconActiveColor)

        // Permissions field
        GenericField(
            label = R.string.settings_permissions_label,
            onClick = { }
        )
        HorizontalDivider(color = GuardianTheme.colors.iconActiveColor)

        // About field
        GenericField(
            label = R.string.settings_about_label,
            onClick = { }
        )
        HorizontalDivider(color = GuardianTheme.colors.iconActiveColor)

        // Open source field
        GenericField(
            label = R.string.settings_open_source,
            onClick = { }
        )
        HorizontalDivider(color = GuardianTheme.colors.iconActiveColor)

        Spacer(modifier = Modifier.height(36.dp))
        TextBodyMedium(
            "Versão - ${uiState.appVersion}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            OutlinedButton(
                onClick = onSignOutClick
            ) {
                TextBodyMedium(
                    stringResource(R.string.settings_logout_btn),
                    color = GuardianTheme.colors.error
                )
            }
        }
    }
}