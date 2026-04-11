package br.com.bit.guardian.settings.ui.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class SettingsUiState {
    @Parcelize
    data object Loading : SettingsUiState(), Parcelable

    @Parcelize
    data class Success(
        val avatar: Avatar,
        val nickname: NickName,
        val permissions: List<Permission>,
        val appVersion: String
    ) : SettingsUiState(), Parcelable

    @Parcelize
    data object Error : SettingsUiState(), Parcelable
    companion object
}

@Parcelize
data class Permission(val label: String, val key: String, val isChecked: Boolean) : Parcelable

@Parcelize
data class Avatar(
    val icon: Int,
    val isLoading: Boolean = false,
    val isError: Boolean = false
) : Parcelable

@Parcelize
data class NickName(
    val text: String,
    val newValue: String = "",
    val isLoading: Boolean = false,
    val isError: Boolean = false
) : Parcelable
