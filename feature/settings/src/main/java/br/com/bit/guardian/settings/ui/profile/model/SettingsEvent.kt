package br.com.bit.guardian.settings.ui.profile.model

sealed class SettingsEvent {
    data object  SignOutSuccess : SettingsEvent()
    data object SignOutError : SettingsEvent()
}