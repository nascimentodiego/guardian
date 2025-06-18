package br.com.bit.guardian.settings.home.domain.mappers

import br.com.bit.guardian.settings.home.data.repository.model.Emergence
import br.com.bit.guardian.settings.home.data.repository.model.Permission
import br.com.bit.guardian.settings.home.data.repository.model.Settings
import br.com.bit.guardian.settings.home.domain.entities.EmergenceEntity
import br.com.bit.guardian.settings.home.domain.entities.PermissionEntity
import br.com.bit.guardian.settings.home.domain.entities.SettingsEntity

fun Settings.toEntity() = SettingsEntity(
    icon = this.icon,
    nickname = this.nickname,
    emergencies = this.emergencies.map { it.toEntity() },
    permissions = this.permissions.map { it.toEntity() }
)

fun Emergence.toEntity() = EmergenceEntity(
    background = this.background,
    description = this.description,
    icon = this.icon,
    phone = this.phone
)

fun Permission.toEntity() = PermissionEntity(
    key = this.key,
    label = this.label,
    isChecked = this.value
)