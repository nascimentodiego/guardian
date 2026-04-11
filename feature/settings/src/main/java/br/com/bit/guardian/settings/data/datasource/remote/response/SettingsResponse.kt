package br.com.bit.guardian.settings.data.datasource.remote.response

data class SettingsResponse(
    val avatar: Int = 0,
    val nickname: String = "",
    val permissions: List<PermissionResponse> = listOf(),
    val emergencies: List<EmergenceResponse> = listOf()
)

data class PermissionResponse(
    val key: String = "",
    val label: String = "",
    val value: Boolean = false
)

data class EmergenceResponse(
    val background: String = "",
    val description: String = "",
    val icon: String = "",
    val phone: String = ""
)