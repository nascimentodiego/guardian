package br.com.bit.guardian.login.ui.model

import br.com.bit.guardian.login.domain.entities.User

sealed interface UserLoginUiState {
    data class Success(val user: User) : UserLoginUiState
    data object Error : UserLoginUiState
    data object Loading : UserLoginUiState
}
