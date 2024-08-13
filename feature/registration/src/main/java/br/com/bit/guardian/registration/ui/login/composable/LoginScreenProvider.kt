package br.com.bit.guardian.registration.ui.login.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.bit.guardian.registration.domain.entities.User
import br.com.bit.guardian.registration.ui.login.model.UserLoginUiState

class LoginScreenProvider: PreviewParameterProvider<UserLoginUiState> {
    override val values = sequenceOf(
        UserLoginUiState.Success(
           User(
                name = "Diego Figueredo do Nascimento",
                email = "nascimento.diego@gmail.com"
            )
        )
    )
}