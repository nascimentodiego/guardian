package br.com.bit.guardian.app.ui.home.navbar.layout


import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMap
import androidx.compose.ui.util.fastMaxBy


fun overlappingRowMeasurePolicy(overlapFactor: Float) = MeasurePolicy { measurables, constraints ->
    val placeables = measurables.map { measurable -> measurable.measure(constraints)}
    val height = placeables.maxOf { it.height }
    val width = (placeables.subList(1,placeables.size).sumOf { it.width  }* overlapFactor + placeables[0].width).toInt()
    layout(width,height) {
        var xPos = 0
        for (placeable in placeables) {
            placeable.placeRelative(xPos, 0, 0f)
            xPos += (placeable.width * overlapFactor).toInt()
        }
    }
}



@Composable
fun GuardianMainLayout(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit
) {

    SubcomposeLayout {  constraints ->
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

private enum class GuardianMainLayoutContent {
    MainContent,
    NavigationRail,
    BottomBar
}
