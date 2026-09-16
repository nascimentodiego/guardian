package br.com.bit.guardian.settings.ui.profile.model

import kotlinx.serialization.Serializable

@Serializable
sealed class SettingsUiState {
    @Serializable
    data object Loading : SettingsUiState()

    @Serializable
    data class Success(
        val avatar: Avatar,
        val nickname: NickName,
        val permissions: List<Permission>,
        val appVersion: String
    ) : SettingsUiState()

    @Serializable
    data object Error : SettingsUiState()
    companion object
}

@Serializable
data class Permission(val label: String, val key: String, val isChecked: Boolean)

@Serializable
data class Avatar(
    val icon: Int,
    val isLoading: Boolean = false,
    val isError: Boolean = false
)

@Serializable
data class NickName(
    val text: String,
    val newValue: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
)
