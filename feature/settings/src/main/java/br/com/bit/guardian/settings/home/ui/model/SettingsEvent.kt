package br.com.bit.guardian.settings.home.ui.model

sealed class SettingsEvent {
    data object  SignOutSuccess : SettingsEvent()
    data object SignOutError : SettingsEvent()
}