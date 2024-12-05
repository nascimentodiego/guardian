package br.com.bit.guardian.core.ui.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun VerticallyAnimatedContent(
    delayAnimation: Duration = Duration.FIRST_START,
    label: String = "VerticallyAnimatedContent",
    content: @Composable () -> Unit
) {
    var visible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit, block = {
        coroutineScope.launch {
            delay(delayAnimation.time.toLong())
            visible = true
        }
    })

    AnimatedVisibility(
        visible = visible,
        label = label,
        enter = fadeIn() + slideInVertically()
    ) {
        content()
    }
}
