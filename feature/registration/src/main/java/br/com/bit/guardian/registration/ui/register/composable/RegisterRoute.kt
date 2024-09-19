package br.com.bit.guardian.registration.ui.register.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.registration.ui.register.RegisterViewModel
import br.com.bit.guardian.registration.ui.register.model.RegistrationEvent

@Composable
fun RegisterRoute(
    viewModel: RegisterViewModel,
    onBackPress: () -> Unit,
    onErrorListener: () -> Unit,
    onSuccessFinishScreen: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val events = viewModel.events.collectAsStateWithLifecycle(initialValue = null).value

    LaunchedEffect(events) {
        events?.let {
            when (it) {
                is RegistrationEvent.Finish -> onSuccessFinishScreen.invoke()
                is RegistrationEvent.Error -> onErrorListener.invoke()
            }
        }
    }

    RegisterScreen(
        uiState = uiState,
        intent = viewModel::onIntent,
        onBackPressClickListener = onBackPress
    )
}
