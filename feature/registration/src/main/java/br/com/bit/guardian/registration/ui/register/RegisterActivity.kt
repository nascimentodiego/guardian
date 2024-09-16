package br.com.bit.guardian.registration.ui.register

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.registration.ui.register.composable.RegisterRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : ComponentActivity() {
    private val viewModel: RegisterViewModel by viewModels()

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
                RegisterRoute(
                    viewModel,
                    onError = { it ->
                        Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
                    }, onBackPress = {
                        finish()
                    }
                )
            }
        }
    }
}