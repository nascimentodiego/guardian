package br.com.bit.guardian.settings.ui.profile.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class SettingsUiState {
    @Parcelize
    data object Loading : SettingsUiState(), Parcelable

    @Parcelize
    data class Success(
        val icon: Int,
        val nickname: String,
        val permissions: List<Permission>,
        val appVersion: String
    ) : SettingsUiState(), Parcelable

    @Parcelize
    data object Error : SettingsUiState(), Parcelable
    companion object
}

@Parcelize
data class Permission(val label: String, val key: String, val isChecked: Boolean) : Parcelable
