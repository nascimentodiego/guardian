package br.com.bit.guardian.registration.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.extensions.intentDeepLink
import br.com.bit.guardian.core.ui.routes.HOME
import br.com.bit.guardian.registration.ui.login.composable.LoginRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT)
        )
        setContent {
            GuardianTheme(
                isStatusBarTranslucent = true
            ) {
                LoginRoute(viewModel,
                    {
                        startActivity(Intent(baseContext.intentDeepLink(HOME)))
                        finish()
                    },
                    {
                        Toast.makeText(baseContext, "Deu ruim !", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
}