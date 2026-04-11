package br.com.bit.guardian.settings.ui.profile.model

sealed class SettingsEvent {
    class SaveNickNameSuccess : SettingsEvent()
    class SaveAvatarSuccess : SettingsEvent()
    data object SignOutSuccess : SettingsEvent()
    data object SignOutError : SettingsEvent()
}