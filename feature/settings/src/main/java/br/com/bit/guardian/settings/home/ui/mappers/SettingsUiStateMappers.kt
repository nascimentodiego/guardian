package br.com.bit.guardian.settings.home.ui.mappers

import br.com.bit.guardian.settings.home.domain.entities.PermissionEntity
import br.com.bit.guardian.settings.home.domain.entities.SettingsEntity
import br.com.bit.guardian.settings.home.ui.model.Permission
import br.com.bit.guardian.settings.home.ui.model.SettingsUiState

fun SettingsEntity.toUiState(appVersion: String = "") = SettingsUiState.Success(
    icon = this.icon,
    nickname = this.nickname,
    permissions = this.permissions.map { it.toUiState() },
    appVersion = appVersion

)

fun PermissionEntity.toUiState() = Permission(
    label = this.label,
    key = this.key,
    isChecked = this.isChecked
)

