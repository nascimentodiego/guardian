package br.com.bit.guardian.registration.ui.login.model

sealed class LoginEvent {
    class Success : LoginEvent()
    class Error : LoginEvent()
}