package br.com.bit.guardian.registration.ui.login.composable

import android.content.Intent
import androidx.compose.runtime.Composable

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.bit.guardian.registration.ui.login.LoginListener
import br.com.bit.guardian.registration.ui.login.LoginViewModel
import br.com.bit.guardian.registration.ui.register.RegisterActivity

@Composable
fun LoginRoute(viewModel: LoginViewModel) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    val listener = object : LoginListener {
        override fun onCreateUserClickListener() {
            context.startActivity(Intent(context, RegisterActivity::class.java))
        }

        override fun onLoginClickListener(email: String, password: String) {
            TODO("Not yet implemented")
        }
    }

    LoginScreen(uiState = uiState, callbacks = listener)
}
