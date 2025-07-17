@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.bit.guardian.settings.ui.profile

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.home.HomeContentWidget
import br.com.bit.guardian.feature.settings.R
import br.com.bit.guardian.settings.ui.profile.composable.SettingsError
import br.com.bit.guardian.settings.ui.profile.composable.SettingsLoading
import br.com.bit.guardian.settings.ui.profile.composable.SettingsSuccess
import br.com.bit.guardian.settings.ui.profile.model.SettingsEvent
import br.com.bit.guardian.settings.ui.profile.model.SettingsUiState

@Composable
fun SettingsRoute(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val activity = LocalActivity.current

    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val events = viewModel.events.collectAsStateWithLifecycle(initialValue = null).value

    var showNicknameBottomSheet by remember { mutableStateOf(false) }
    var showAvatarBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()

    HomeContentWidget(
        modifier = modifier.padding(GuardianTheme.dimens.spacingM),
        title = R.string.settings_screen_title,
        icon = GuardianIcon.Settings,
        backgroundIcon = br.com.bit.guardian.core.designsystem.R.drawable.ds_bg_circle,
        iconColor = GuardianTheme.colors.iconActiveColor,
        backgroundIconColor = GuardianTheme.colors.success
    ) {
        when (uiState) {
            is SettingsUiState.Success -> SettingsSuccess(
                uiState = uiState,
                showNicknameBottomSheet = showNicknameBottomSheet,
                showAvatarBottomSheet = showAvatarBottomSheet,
                sheetState = sheetState,
                onEditAvatarClick = { showAvatarBottomSheet = true },
                onEditNickNameClick = { showNicknameBottomSheet = true },
                onNickNameTextChange = viewModel::onNickNameTextChange,
                onConfirmNickname = viewModel::onConfirmNickName,
                onConfirmAvatar = viewModel::onConfirmAvatar,
                onSignOutClick = viewModel::signOut,
                onDismissRequest = {
                    showNicknameBottomSheet = false
                    showAvatarBottomSheet = false
                }
            )

            is SettingsUiState.Loading -> SettingsLoading()
            is SettingsUiState.Error, null -> SettingsError(retry = viewModel::fetchSettings)
        }

    }

    LaunchedEffect(events) {
        events?.let {
            when (it) {
                is SettingsEvent.SignOutSuccess -> {
                    activity?.finish()
                }

                is SettingsEvent.SignOutError -> {
                    activity?.baseContext?.let {
                        Toast.makeText(it, "Deu ruim !", Toast.LENGTH_LONG).show()
                    }
                }

                is SettingsEvent.SaveNickNameSuccess -> {
                    showNicknameBottomSheet = false
                }

                is SettingsEvent.SaveAvatarSuccess -> {
                    showAvatarBottomSheet = false
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsRoutePreview() {
    GuardianTheme {
        SettingsRoute()
    }
}