package br.com.bit.guardian.registration.ui.register.composable.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import br.com.bit.guardian.registration.ui.register.model.RegisterUiState
import br.com.bit.guardian.registration.ui.register.model.RegistrationRuleState

class RegistrationScreenProvider : PreviewParameterProvider<RegisterUiState> {
    override val values = sequenceOf(
        RegisterUiState.Success(
            RegistrationRuleState.Empty
        )
    )
}