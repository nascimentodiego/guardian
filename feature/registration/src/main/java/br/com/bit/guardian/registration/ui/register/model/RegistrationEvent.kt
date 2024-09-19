package br.com.bit.guardian.registration.ui.register.model

sealed class RegistrationEvent {
     class Finish : RegistrationEvent()
     class Error : RegistrationEvent()
}