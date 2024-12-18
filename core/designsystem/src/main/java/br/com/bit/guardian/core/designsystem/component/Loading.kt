package br.com.bit.guardian.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadingComponent(
    roundSize: Dp = 8.dp,
    height: Dp = 64.dp,
    width:Dp = 64.dp
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(roundSize))
            .background(GuardianTheme.colors.surface)
            .width(width)
            .height(height)
            .shimmer()
    ) {
        Box(
            modifier = Modifier
                .width(width)
                .height(height)
                .background(GuardianTheme.colors.background)
        )
    }
}

@Composable
@ThemePreviews
fun LoadingPreview() {
    GuardianTheme {
        LoadingComponent()
    }
}