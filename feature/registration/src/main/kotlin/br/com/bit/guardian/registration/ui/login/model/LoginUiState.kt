package br.com.bit.guardian.registration.ui.login.model

import br.com.bit.guardian.registration.ui.login.model.UserView.Companion.Empty
import kotlinx.serialization.Serializable

@Serializable
sealed class LoginUiState(val userView: UserView = Empty) {
    @Serializable
    data class Idle(val user: UserView) : LoginUiState(user)

    @Serializable
    data object Loading : LoginUiState()
}

@Serializable
data class UserView(
    val email: String,
    val password: String,
    val isButtonEnabled: Boolean,
    val isButtonLoading: Boolean
) {
    companion object {
        val Empty = UserView(
            email = "",
            password = "",
            isButtonEnabled = false,
            isButtonLoading = false
        )
    }
}
