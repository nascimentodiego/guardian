package br.com.bit.guardian.registration.ui.register.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.registration.ui.register.RegisterViewModel
import br.com.bit.guardian.registration.ui.register.model.Event

@Composable
fun RegisterRoute(viewModel: RegisterViewModel, onBackPress: () -> Unit,onError:(String)-> Unit) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val events = viewModel.events.collectAsStateWithLifecycle(initialValue = null).value

    LaunchedEffect(events) {
        events?.let {
            when(it) {
                is Event.Error -> {
                    onError(it.msg)
                }
            }
        }
    }


    RegisterScreen(
        uiState = uiState,
        putEmail = viewModel::putEmail,
        putPassword = viewModel::putPassword,
        putConfPassword = viewModel::putConfirmPassword,
        onClickRegisterUser = viewModel::sendEvent,
        onBackPressClickListener = onBackPress
    )
}
