package br.com.bit.guardian.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import com.valentinilk.shimmer.shimmer

@Composable
@ThemePreviews
fun LoadingPreview() {
    GuardianTheme {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .size(128.dp)
                .shimmer()
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Red)
            )
        }
    }
}