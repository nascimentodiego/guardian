package br.com.bit.guardian.settings.data.repository.mappers

import br.com.bit.guardian.settings.data.datasource.remote.response.EmergenceResponse
import br.com.bit.guardian.settings.data.datasource.remote.response.PermissionResponse
import br.com.bit.guardian.settings.data.datasource.remote.response.SettingsResponse
import br.com.bit.guardian.settings.data.repository.model.Emergence
import br.com.bit.guardian.settings.data.repository.model.Permission
import br.com.bit.guardian.settings.data.repository.model.Settings

fun SettingsResponse.toSettings() = Settings(
    icon = this.avatar,
    nickname = this.nickname,
    permissions = this.permissions.map { it.toPermission() },
    emergencies = this.emergencies.map { it.toEmergence() }
)

fun PermissionResponse.toPermission() = Permission(
    label = this.label,
    key = this.key,
    value = this.value
)

fun EmergenceResponse.toEmergence() = Emergence(
    background = this.background,
    description = this.description,
    icon = this.icon,
    phone = this.phone
)