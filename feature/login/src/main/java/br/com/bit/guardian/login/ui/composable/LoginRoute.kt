package br.com.bit.guardian.login.ui.composable

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.login.ui.LoginViewModel

@Composable
fun LoginRoute(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    LoginScreen(uiState =  uiState)
}
