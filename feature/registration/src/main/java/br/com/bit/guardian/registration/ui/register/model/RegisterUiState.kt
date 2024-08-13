package br.com.bit.guardian.registration.ui.register.model

import br.com.bit.guardian.registration.domain.entities.User

sealed interface RegisterUiState {
    data class Success(val user: User) : RegisterUiState
    data object Error : RegisterUiState
    data object Loading : RegisterUiState
}
