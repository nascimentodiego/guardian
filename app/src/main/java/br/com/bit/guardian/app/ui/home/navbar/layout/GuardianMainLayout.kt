package br.com.bit.guardian.app.ui.home.navbar.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.util.fastFirst
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMaxBy

fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints) }
    val height = placeables.maxOf { it.height }
    val width = (
        placeables.subList(1, placeables.size)
            .sumOf { it.width } * overlapFactor + placeables[0].width
        ).toInt()
    layout(width, height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}

@Composable
fun GuardianBottomBarLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        layout(layoutWidth, layoutHeight) {
            val bottomBarPlaceables =
                subcompose(GuardianMainLayoutContent.BottomBar) { bottomBar() }
                    .fastMap { it.measure(looseConstraints) }

            val bottomBarHeight = bottomBarPlaceables.fastMaxBy { it.height }?.height

            val contentPlaceables =
                subcompose(GuardianMainLayoutContent.MainContent) {
                    content()
                }.fastMap { it.measure(looseConstraints) }

            contentPlaceables.fastForEach { it.place(0, 0) }

            // The bottom bar is always at the bottom of the layout
            bottomBarPlaceables.fastForEach { it.place(0, layoutHeight - (bottomBarHeight ?: 0)) }
        }
    }
}

private const val NavigationSuiteLayoutIdTag = "navigationSuite"
private const val ContentLayoutIdTag = "content"

@Composable
fun GuardianRailLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    navRail: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        content = {
            Box(Modifier.layoutId(NavigationSuiteLayoutIdTag)) { navRail() }
            Box(Modifier.layoutId(ContentLayoutIdTag)) { content() }
        }
    ) { measurables, constraints ->
        val looseConstraints = constraints.copy(minWidth = 0, minHeight = 0)
        val layoutHeight = constraints.maxHeight
        val layoutWidth = constraints.maxWidth

        val navigationRailPlaceable =
            measurables
                .fastFirst { it.layoutId == NavigationSuiteLayoutIdTag }
                .measure(looseConstraints)

        val contentPlaceable =
            measurables
                .fastFirst { it.layoutId == ContentLayoutIdTag }
                .measure(
                    looseConstraints.copy(
                        maxWidth = layoutWidth - navigationRailPlaceable.measuredWidth
                    )
                )

        layout(layoutWidth, layoutHeight) {
            contentPlaceable.placeRelative(navigationRailPlaceable.width, 0)
            navigationRailPlaceable.placeRelative(0, 0)
        }
    }
}

private enum class GuardianMainLayoutContent {
    MainContent,
    BottomBar
}
