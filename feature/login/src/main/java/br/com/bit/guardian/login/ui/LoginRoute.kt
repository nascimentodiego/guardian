package br.com.bit.guardian.login.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.SimpleButton
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.login.R
import br.com.bit.guardian.login.ui.model.UserLoginUiState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginRoute(viewModel: LoginViewModel) {

    val uiState = viewModel.uiState.collectAsState()

    val firstColor = GuardianTheme.colors.primary.copy(alpha = 0.9f)
    val secondColor = GuardianTheme.colors.primary

    val largeRadialGradient = object : ShaderBrush() {
        override fun createShader(size: Size): Shader {
            val biggerDimension = maxOf(size.height, size.width)
            return RadialGradientShader(
                colors = listOf(
                    firstColor, secondColor
                ),
                center = size.center,
                radius = biggerDimension / 2f,
                colorStops = listOf(0f, 0.95f)
            )
        }
    }


    uiState.value?.let { it ->
        when (it) {
            is UserLoginUiState.Loading -> {
                Text(
                    text = "Loading...",
                    style = GuardianTheme.typography.titleSmall
                )
            }

            is UserLoginUiState.Success -> {
                Column {
                    it.user.run { ->
                        Text(
                            text = "$name - $email",
                            style = GuardianTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }

            is UserLoginUiState.Error -> {
                Text(text = "Ocorreu um Error !")
            }
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = largeRadialGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//       LoadingScreen()
        val context = LocalContext.current
        SimpleButton(titleRes = R.string.login_btn_login, onClick = {

            viewModel.createNewUser("diego.nascimento@gmail.com", "123456789")

            /*  val auth = Firebase.auth
            auth.createUserWithEmailAndPassword("diego.nasc@gmai.com", "123456789")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            context,
                            "Authentication Success",
                            Toast.LENGTH_SHORT,
                        ).show()
                    } else {
                        Toast.makeText(
                            context,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }*/

        })

    }
}
