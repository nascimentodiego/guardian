package br.com.bit.guardian.login.ui.composable.expanded

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.login.R as RL
import br.com.bit.guardian.core.designsystem.component.TextBodyLarge
import br.com.bit.guardian.core.designsystem.component.TextBodyMedium
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.component.TextTitleMedium
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import kotlinx.coroutines.delay

@Composable
fun BitPlatformHighlight(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ds_ic_bit),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(280.dp)
                .height(180.dp)
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))

        val density = LocalDensity.current
        var visible by remember { mutableStateOf(true) }
        var messageIndex by remember { mutableIntStateOf(0) }

        LaunchedEffect(visible) {
            val timeToInvert = if (visible) 5000L else 300L
            delay(timeToInvert)

            if (!visible) {
                if (messageIndex >= bitHighlightMessages.size-1) {
                    messageIndex = 0
                } else {
                    ++messageIndex
                }
            }
            visible = !visible
        }

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally {
                // Slide in from 40 dp from the top.
                with(density) { -40.dp.roundToPx() }
            } + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutHorizontally {
                with(density) { -40.dp.roundToPx() }
            } + shrinkVertically() + fadeOut()
        ) {
            TextTitleMedium(
                titleRes = bitHighlightMessages[messageIndex].titleRes,
                color = Color.White

            )
        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))

        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally {
                // Slide in from 40 dp from the top.
                with(density) { 80.dp.roundToPx() }
            } + fadeIn(
                // Fade in with the initial alpha of 0.3f.
                initialAlpha = 0.3f
            ),
            exit = slideOutHorizontally {
                with(density) { 80.dp.roundToPx() }
            } + shrinkVertically() + fadeOut()
        ) {
            TextBodySmall(
                stringRes = bitHighlightMessages[messageIndex].messageRes,
                modifier = Modifier.padding(horizontal = 56.dp,),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

val bitHighlightMessages = listOf(
    BitMessage(RL.string.login_highlight_title_one, RL.string.login_highlight_message_one),
    BitMessage(RL.string.login_highlight_title_two, RL.string.login_highlight_message_two),
    BitMessage(RL.string.login_highlight_title_Three, RL.string.login_highlight_message_Three)
)

data class BitMessage(@StringRes val titleRes: Int, @StringRes val messageRes: Int)


@Preview
@Composable
fun BitPlatformHighlightPreview() {
    GuardianTheme {
        BitPlatformHighlight()
    }
}