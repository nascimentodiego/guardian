package br.com.bit.guardian.core.designsystem.theme

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.Shape

@Composable
fun Modifier.backgroundGradientPrimary(shape: Shape? = null): Modifier {
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
    return if (shape == null)
        this.background(largeRadialGradient)
    else
        this.background(largeRadialGradient, shape)
}
