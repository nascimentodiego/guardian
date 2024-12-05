package br.com.bit.guardian.core.designsystem.icon.animations

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun GeometricAnimatedIcon(
    modifier: Modifier,
    size: Dp = 25.dp,
    @DrawableRes icon: Int,
    @DrawableRes backgroundIcon: Int,
    iconColor: Color,
    backgroundIconColor: Color
) {
    val vector = ImageVector.vectorResource(id = icon)
    val painterIcon = rememberVectorPainter(image = vector)

    val backgroundVector = ImageVector.vectorResource(id = backgroundIcon)
    val backgroundPainter = rememberVectorPainter(image = backgroundVector)

    var canvasSize by remember { mutableStateOf(Size.Zero) }

    val density = LocalDensity.current
    val sizePx = remember(size) { size.toPxf(density) }


    Canvas(
        modifier = modifier
            .size(size * 1.1f)
            .onGloballyPositioned { canvasSize = it.size.toSize() }
    ) {

        with(backgroundPainter) {
            translate(
                left = 20.toDp().toPx(),
                top = 20.toDp().toPx()
            ) {
                draw(
                    size = Size(
                        backgroundPainter.intrinsicSize.width * .9f,
                        backgroundPainter.intrinsicSize.height * .9f
                    ),
                    colorFilter = ColorFilter.tint(color = backgroundIconColor)
                )
            }
        }

        with(painterIcon) {
            draw(
                size = Size(sizePx, sizePx),
                colorFilter = ColorFilter.tint(color = iconColor)
            )
        }
    }
}

@Preview
@Composable
fun GeometricAnimatedIconPreview() {
    GuardianTheme {
        Row(horizontalArrangement = Arrangement.Absolute.SpaceEvenly) {

            GeometricAnimatedIcon(
                modifier = Modifier,
                icon = GuardianIcon.Home,
                backgroundIcon = R.drawable.ds_bg_circle,
                iconColor = GuardianTheme.colors.iconActiveColor,
                backgroundIconColor = GuardianTheme.colors.warning
            )

            GeometricAnimatedIcon(
                modifier = Modifier,
                icon = GuardianIcon.Devices,
                backgroundIcon = R.drawable.ds_bg_polygon,
                iconColor = GuardianTheme.colors.iconActiveColor,
                backgroundIconColor = GuardianTheme.colors.error
            )
        }

    }
}