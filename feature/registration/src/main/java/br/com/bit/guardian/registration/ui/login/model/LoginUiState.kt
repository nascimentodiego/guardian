package br.com.bit.guardian.registration.ui.login.model

import android.os.Parcelable
import br.com.bit.guardian.registration.ui.login.model.UserView.Companion.Empty
import kotlinx.parcelize.Parcelize

sealed class LoginUiState(val userView: UserView = Empty) {
    @Parcelize
    data class Idle(val user: UserView) : LoginUiState(user), Parcelable

    @Parcelize
    data object Loading : LoginUiState(), Parcelable
}

@Parcelize
data class UserView(val email: String, val password: String, val isLoadingButton: Boolean) :
    Parcelable {
    companion object {
        val Empty = UserView("", "", false)
    }
}
