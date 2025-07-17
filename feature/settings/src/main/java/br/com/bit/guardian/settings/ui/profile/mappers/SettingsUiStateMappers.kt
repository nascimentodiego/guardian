package br.com.bit.guardian.settings.ui.profile.mappers

import br.com.bit.guardian.settings.domain.entities.PermissionEntity
import br.com.bit.guardian.settings.domain.entities.SettingsEntity
import br.com.bit.guardian.settings.ui.profile.model.Avatar
import br.com.bit.guardian.settings.ui.profile.model.NickName
import br.com.bit.guardian.settings.ui.profile.model.Permission
import br.com.bit.guardian.settings.ui.profile.model.SettingsUiState

fun SettingsEntity.toUiState(appVersion: String = "") = SettingsUiState.Success(
    avatar = Avatar(icon = this.icon),
    nickname = NickName(text = this.nickname),
    permissions = this.permissions.map { it.toUiState() },
    appVersion = appVersion

)

fun PermissionEntity.toUiState() = Permission(
    label = this.label,
    key = this.key,
    isChecked = this.isChecked
)

