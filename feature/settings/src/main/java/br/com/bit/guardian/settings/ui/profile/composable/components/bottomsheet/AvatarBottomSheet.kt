@file:OptIn(ExperimentalMaterial3Api::class)

package br.com.bit.guardian.settings.ui.profile.composable.components.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.R as RDs
import br.com.bit.guardian.core.designsystem.component.LoadedButton
import br.com.bit.guardian.core.designsystem.component.LoadedTertiaryButton
import br.com.bit.guardian.core.designsystem.component.TextTitleSmall
import br.com.bit.guardian.core.designsystem.extension.GuardianThemePreviews
import br.com.bit.guardian.core.designsystem.extension.withState
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon.avatarIcons
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.settings.R
import br.com.bit.guardian.settings.ui.profile.composable.components.AvatarSelectableItem
import br.com.bit.guardian.settings.ui.profile.model.Avatar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun AvatarBottomSheet(
    modifier: Modifier = Modifier,
    showBottomSheet: Boolean = false,
    avatar: Avatar,
    sheetState: SheetState = rememberModalBottomSheetState(),
    scope: CoroutineScope = rememberCoroutineScope(),
    onDismissRequest: () -> Unit,
    onConfirm: (Int) -> Unit
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
            SelectAvatarContent(
                modifier = modifier,
                init = avatar.icon,
                isLoading = avatar.isLoading,
                onCancel = { cancelEvent.invoke() },
                onConfirm = onConfirm
            )
        }
    }
}

@Composable
internal fun SelectAvatarContent(
    modifier: Modifier = Modifier,
    init: Int = 0,
    isLoading: Boolean = false,
    onCancel: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var selected by rememberSaveable { mutableIntStateOf(init) }
    val listOfAvatar by rememberSaveable { mutableStateOf(avatarIcons) }

    Column(modifier = modifier.padding(horizontal = GuardianTheme.dimens.spacingM)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            stickyHeader {
                Column {
                    TextTitleSmall(stringResource(R.string.settings_avatar_label))
                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }

            avatarIcons.forEach { avatar ->
                item(key = avatar.key) {
                    Box(contentAlignment = Alignment.Center) {
                        AvatarSelectableItem(
                            avatar = avatar.key,
                            isSelected = avatar.key == selected,
                            onSelectClick = { selected = avatar.key }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
        Row(modifier = Modifier) {
            LoadedTertiaryButton(
                onClick = onCancel,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = RDs.string.ds_button_cancel),
                    color = Color.White.withState(enabled = true)
                )
            }
            Spacer(modifier = modifier.width(GuardianTheme.dimens.spacingXXS))
            LoadedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                onClick = { onConfirm.invoke(selected) },
                isLoading = isLoading
            ) {
                Text(
                    text = stringResource(id = RDs.string.ds_button_confirm),
                    color = Color.White.withState(enabled = true)
                )
            }
        }
    }
}

@GuardianThemePreviews
@Composable
fun SelectAvatarContentPreview() {
    GuardianTheme {
        Surface {
            SelectAvatarContent(
                onCancel = {},
                onConfirm = { }
            )
        }
    }
}