package br.com.bit.guardian.registration.ui.login.composable

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.LoginViewModel
import br.com.bit.guardian.registration.ui.login.model.LoginEvent
import br.com.bit.guardian.registration.ui.register.RegisterActivity

@Composable
fun LoginRoute(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
    onLoginError: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val events = viewModel.events.collectAsStateWithLifecycle(initialValue = null).value
    val context = LocalContext.current

    val listener = object : LoginListener {
        override fun onCreateUserClickListener() {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }
    }

    LaunchedEffect(Unit) {
        viewModel.isUserLogged()
    }

    LaunchedEffect(events) {
        events?.let {
            when (it) {
                is LoginEvent.Success -> {
                    onLoginSuccess.invoke()
                }

                is LoginEvent.Error -> {
                    onLoginError.invoke()
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        intent = viewModel::onIntent,
        callbacks = listener
    )

}
