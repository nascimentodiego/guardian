package br.com.bit.guardian.app.ui.home.navbar.shape

import android.graphics.Canvas
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme


class BottomBarShape() : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(
        Path().apply {
            moveTo(0f, 0f)

            lineTo(50f,0f)

            lineTo(50f,100f)
            lineTo(100f,100f)
            lineTo(100f,0f)
            lineTo(size.width,0f)
            lineTo(size.width,size.height)
            lineTo(0f,size.height)


//            lineTo(size.width,0f)
//            lineTo(size.width, size.height)
//            lineTo(0f, size.height)

            close()

        }
    )
}

@Composable
fun Modifier.drawVerticalTopGradient(
    vararg colorStops: Pair<Float, Color>,
    contentColor:Color,
) = this.drawBehind {
    val path = Path()
    path.moveTo(size.width, 0f)
    path.lineTo(size.width, size.height)
    path.lineTo(0f, size.height)



    val brush = Brush.verticalGradient(colorStops = colorStops)

    drawRect(
        brush = brush,
        size = Size(
            size.width,
            12.dp.toPx()
        )
    )

    drawRect(
        SolidColor(contentColor),
        topLeft = Offset(0f, 12.dp.toPx()),
        size = Size(
            size.width,
            size.height
        )
    )
}

@Preview
@Composable
fun ShapePreview() {
    GuardianTheme {

        val color = GuardianTheme.colors.primary
        Row(
            modifier = Modifier
                .drawBehind {
                    val path = Path()
                    path.moveTo(size.width, 0f)
                    path.lineTo(size.width, size.height)
                    path.lineTo(0f, size.height)

                    val brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            color
                        )
                    )

                    drawRect(
                        brush = brush,
                        size = Size(
                            size.width,
                            6.dp.toPx()
                        )
                    )


                    drawRect(
                        SolidColor(color),
                        topLeft = Offset(0f, 6.dp.toPx()),
                        size = Size(
                            size.width,
                            size.height
                        )
                    )
                }
                .fillMaxWidth()
                .height(80.dp),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

        }
    }
}