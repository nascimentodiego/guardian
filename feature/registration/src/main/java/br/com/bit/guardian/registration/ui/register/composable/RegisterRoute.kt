package br.com.bit.guardian.registration.ui.register.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.registration.ui.register.RegisterViewModel

@Composable
fun RegisterRoute(viewModel: RegisterViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value


}
