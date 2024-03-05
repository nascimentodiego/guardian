package br.com.bit.guardian.login.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.login.ui.model.UserLoginUiState

@Composable
fun LoginScreen(uiState: State<UserLoginUiState?>) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = largeRadialGradient),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
    }
}