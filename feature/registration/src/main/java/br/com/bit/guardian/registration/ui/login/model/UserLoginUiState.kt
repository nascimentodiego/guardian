package br.com.bit.guardian.registration.ui.login.model

import br.com.bit.guardian.registration.domain.entities.User

sealed interface UserLoginUiState {
    data class Success(val user: User) :
        UserLoginUiState
    data object Error : UserLoginUiState
    data object Loading : UserLoginUiState
}
