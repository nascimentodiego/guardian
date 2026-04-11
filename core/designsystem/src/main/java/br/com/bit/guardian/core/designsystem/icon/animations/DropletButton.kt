package br.com.bit.guardian.core.designsystem.icon.animations

import androidx.annotation.FloatRange
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import kotlin.math.max

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DropletButton(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onClick: () -> Unit,
    icon: Int,
    contentDescription: String? = null,
    iconColor: Color = Color.LightGray,
    dropletColor: Color = Color.Red,
    size: Dp = 20.dp,
    animationSpec: AnimationSpec<Float> = remember { tween(300) }
) {
    Box(
        modifier = modifier.noRippleClickable { onClick() }
    ) {
        val density = LocalDensity.current
        val dropletButtonParams = animateDropletButtonAsState(
            isSelected = isSelected,
            animationSpec = animationSpec,
            size = size.toPxf(density)
        )

        val sizePx = remember(size) { size.toPxf(density) }
        val circleCenter by remember {
            derivedStateOf {
                mutableStateOf(sizePx / 2)
            }
        }

        val vector = ImageVector.vectorResource(id = icon)
        val painter = rememberVectorPainter(image = vector)
        Canvas(
            modifier = Modifier
                .size(size)
                .align(Alignment.Center)
                .graphicsLayer(
                    alpha = 0.99f,
                    scaleX = dropletButtonParams.value.scale,
                    scaleY = dropletButtonParams.value.scale
                ),
            contentDescription = contentDescription ?: ""
        ) {
            with(painter) {
                draw(
                    size = Size(sizePx, sizePx),
                    colorFilter = ColorFilter.tint(color = iconColor)
                )
            }

            drawCircle(
                color = dropletColor,
                radius = dropletButtonParams.value.radius,
                center = Offset(
                    circleCenter.value,
                    dropletButtonParams.value.verticalOffset - 20f
                ),
                blendMode = BlendMode.SrcIn
            )
        }
    }
}

@Stable
data class DropletButtonParams(
    @FloatRange(from = 0.0, to = 1.0) val scale: Float = 1f,
    val radius: Float = 10f,
    val verticalOffset: Float = 0f
)

@Composable
internal fun animateDropletButtonAsState(
    isSelected: Boolean,
    animationSpec: AnimationSpec<Float> = remember { tween(300) },
    size: Float
): State<DropletButtonParams> {
    val fraction = animateFloatAsState(
        targetValue = if (isSelected) 1f else 0f,
        animationSpec = animationSpec
    )
    val isAnimationRequired by rememberUpdatedState(newValue = isSelected)

    return produceState(
        initialValue = DropletButtonParams(),
        key1 = fraction.value
    ) {
        this.value = this.value.copy(
            scale = if (isAnimationRequired) scaleInterpolation(fraction.value) else 1f,
            radius = if (isAnimationRequired) lerp(0f, size, fraction.value) else 0f,
            verticalOffset = lerp(0f, size, fraction.value)
        )
    }
}

fun scaleInterpolation(fraction: Float): Float {
    val f = if (fraction < 0.3f) {
        fraction * 3.33f
    } else {
        max((0.6f - fraction) * 3.33f, 0f)
    }
    return 1f - 0.2f * f
}

fun Modifier.noRippleClickable(
    onClick: () -> Unit
) = composed {
    this.clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}

@Stable
fun Float.toDp(density: Density): Dp = with(density) { this@toDp.toDp() }

@Stable
fun Dp.toPxf(density: Density): Float = with(density) { this@toPxf.toPx() }

@Stable
@Composable
fun Dp.toPxf(): Float = toPxf(LocalDensity.current)

@Stable
@Composable
fun Float.toDp() = this.toDp(LocalDensity.current)