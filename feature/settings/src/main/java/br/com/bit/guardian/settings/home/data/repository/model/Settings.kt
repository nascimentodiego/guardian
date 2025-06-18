package br.com.bit.guardian.settings.home.data.repository.model

data class Settings(
    val icon: Int = 0,
    val nickname: String = "",
    val permissions: List<Permission> = listOf(),
    val emergencies: List<Emergence> = listOf(),
)

data class Permission(
    val key:String = "",
    val label: String = "",
    val value: Boolean = false
)

data class Emergence(
    val background: String = "",
    val description: String = "",
    val icon: String = "",
    val phone: String = ""
)