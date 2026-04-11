package br.com.bit.guardian.settings.domain.entities

data class SettingsEntity(
    val icon: Int = 0,
    val nickname: String = "",
    val permissions: List<PermissionEntity> = listOf(),
    val emergencies: List<EmergenceEntity> = listOf()
)

data class EmergenceEntity(
    val background: String = "",
    val description: String = "",
    val icon: String = "",
    val phone: String = ""
)

data class PermissionEntity(
    val key: String = "",
    val label: String = "",
    val isChecked: Boolean = false
)
