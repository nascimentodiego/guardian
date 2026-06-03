package br.com.bit.guardian.registration.ui.login.composable

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.bit.guardian.core.common.ExcludeFromGeneratedReport
import br.com.bit.guardian.registration.ui.login.model.LoginUiState
import br.com.bit.guardian.registration.ui.login.model.UserView

@ExcludeFromGeneratedReport
class LoginScreenProvider : PreviewParameterProvider<LoginUiState> {
    override val values: Sequence<LoginUiState> = sequenceOf(
        LoginUiState.Idle(
            UserView(
                email = "nascimento.diego@gmail.com",
                password = "d!@sdsafg!@425278",
                isButtonLoading = false,
                isButtonEnabled = false
            )
        ),
        LoginUiState.Loading
    )
}