package br.com.bit.guardian.app.ui.home.navrail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app.ui.destination.AppDestinations
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import kotlinx.coroutines.delay


@Composable
fun GuardianNavRail(
    modifier: Modifier = Modifier,
    initialDestination: AppDestinations = AppDestinations.HOME,
    entries: List<AppDestinations>,
    onSelectedItem: (destination: AppDestinations) -> Unit
) {

    var visible by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = Unit, block = {
        visible = true
    })

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(initialAlpha = 0.0f) + slideInHorizontally(
            tween(
                durationMillis = 600,
                delayMillis = 300,
                easing = FastOutSlowInEasing
            )
        )
    ) {
        Column(
            modifier = modifier
                .width(100.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .background(
                        GuardianTheme.colors.primaryContainer, RailShape
                    )
                    .offset(x = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingH))
                NailContent(initialDestination, entries, onSelectedItem)
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingH))
            }
        }
    }
}

@Composable
fun NailContent(
    initialDestination: AppDestinations,
    entries: List<AppDestinations>,
    onSelectedItem: (destination: AppDestinations) -> Unit
) {
    var destinationSelected by remember { mutableStateOf(initialDestination) }

    val icons = @Composable {
        entries.forEachIndexed { index, it ->
            NavRailItem(
                pos = index,
                destination = it,
                isSelected = it == destinationSelected,
            ) { dest ->
                destinationSelected = dest
                onSelectedItem(dest)
            }
        }
    }

    NavRailItemLayout(icons = icons)
}

@Composable
fun NavRailItemLayout(
    modifier: Modifier = Modifier,
    icons: @Composable () -> Unit
) {
    val correctSpaceHeight = 12.dp

    Layout(
        modifier = Modifier.then(modifier),
        content = icons
    ) { measurables: List<Measurable>, constraints: Constraints ->

        val placeables: List<Placeable> = measurables.map { it.measure(constraints) }
        val totalWidth = placeables.first().width
        var totalHeight = placeables.sumOf { it.height }
        totalHeight += if (placeables.size == 1) 0 else (correctSpaceHeight * (placeables.size - 1)).roundToPx()

        layout(width = totalWidth, height = totalHeight) {
            val currentX = 0
            var currentY = 0

            placeables.forEach { placeable ->
                placeable.place(x = currentX, y = currentY)
                currentY += placeable.height + correctSpaceHeight.roundToPx()
            }
        }
    }
}

@Composable
fun NavRailItem(
    pos: Int = 1,
    destination: AppDestinations,
    isSelected: Boolean,
    onSelectedItem: (destination: AppDestinations) -> Unit
) {
    val transition = updateTransition(isSelected, label = "item color animation")
    val itemContainerColor by transition.animateColor(
        transitionSpec = {
            tween(200)
        }, label = "color"
    ) { state ->
        when (state) {
            true -> GuardianTheme.colors.iconActiveContainer
            false -> Color.Transparent
        }
    }

    val iconColor by transition.animateColor(
        transitionSpec = {
            tween(200)
        }, label = "color"
    ) { state ->
        when (state) {
            true -> GuardianTheme.colors.iconActiveColor
            false -> GuardianTheme.colors.iconInactiveColor
        }
    }

    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay((800 + (200 * pos)).toLong())
        isVisible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale content - $pos"
    )

    Column(
        modifier = Modifier.scale(scale),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(itemContainerColor, shape = CircleShape)
                .clip(CircleShape)
                .clickable {
                    if (!isSelected) {
                        onSelectedItem(destination)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(destination.icon),
                tint = iconColor,
                modifier = Modifier.size(24.dp),
                contentDescription = stringResource(destination.contentDescription)
            )
        }
        AnimatedVisibility(isSelected) {
            Spacer(modifier = Modifier.height(4.dp))
            TextBodySmall(
                modifier = Modifier.width(56.dp),
                stringRes = destination.label,
                color = GuardianTheme.colors.iconInactiveColor,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }
}

object RailShape : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = drawBackgroundShape(size, density)
        )
    }

    private fun drawBackgroundShape(size: Size, density: Density) = Path().apply {
        val height = size.height

        val widthF = density.run { 80.dp.toPx() }
        val heightF = density.run { 80.dp.toPx() }

        moveTo(0f, 0f)
        cubicTo(
            x1 = 0f, y1 = heightF * .4f,  // 1) control point
            x2 = widthF, y2 = 0f,          // 2) control point
            widthF, heightF                 // destiny point
        )

        lineTo(0f, heightF)
        moveTo(0f, heightF)

        lineTo(widthF, heightF)
        lineTo(widthF, height - heightF)
        lineTo(0f, height - heightF)

        moveTo(0f, height)

        cubicTo(
            x1 = 0f, y1 = height - heightF * .4f, // 1) control point
            x2 = widthF, y2 = height, // 2) control point
            x3 = widthF, y3 = height - heightF // destiny point
        )
        lineTo(0f, height - heightF)
        close()
    }
}

@Preview
@Composable
fun NavRailPreview() {
    GuardianTheme {
        GuardianNavRail(entries = AppDestinations.entries) {}
    }
}

@Composable
fun NavRailCanvasPreview() {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .width(80.dp)
            .height(800.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val widthF = 80.dp.toPx()
            val heightF = 80.dp.toPx()

            val topPath = Path().apply {
                moveTo(0f, 0f)
                cubicTo(
                    x1 = 0f, y1 = heightF, // 1) control point
                    x2 = widthF, y2 = 0f, // 2) control point
                    width, heightF // destiny point
                )
                lineTo(0f, heightF)
                close()
            }

            val bodyPath = Path().apply {
                moveTo(0f, heightF)

                lineTo(width, heightF)
                lineTo(width, height - heightF)
                lineTo(0f, height - heightF)
                close()
            }

            val bottomPath = Path().apply {
                moveTo(0f, height)

                cubicTo(
                    x1 = 0f, y1 = height - heightF, // 1) control point
                    x2 = width, y2 = height, // 2) control point
                    x3 = width, y3 = height - heightF // destiny point
                )
                lineTo(0f, height - heightF)
                close()
            }

            drawPath(topPath, Color.Red)
            drawPath(bodyPath, Color.Red)
            drawPath(bottomPath, Color.Red)

            // 1) Ponto
            drawCircle(
                Color.Blue,
                radius = 15f,
                center = Offset(0f, height - heightF)
            )
            // 2) Ponto
            drawCircle(
                Color.Yellow,
                radius = 15f,
                center = Offset(width, height)
            )

            // Start Point
            drawCircle(
                Color.Black,
                radius = 15f,
                center = Offset(0f, height)
            )

            // Target Point
            drawCircle(
                Color.Black,
                radius = 15f,
                center = Offset(width, height - heightF)
            )
        }
    }
}
