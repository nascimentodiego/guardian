package br.com.bit.guardian.app.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.bit.guardian.app.ui.home.compact.HomeCompact
import br.com.bit.guardian.app.ui.home.expanded.HomeExpanded
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.ui.composable.layout.AdaptiveContent

@Composable
fun HomeScreen() {
    GuardianTheme {

        AdaptiveContent(
            expandedContent = {
                Row(
                    modifier = Modifier.padding(
                        start = GuardianTheme.dimens.spacingM,
                        end = GuardianTheme.dimens.spacingM,
                        top = GuardianTheme.dimens.spacingM
                    ),
                    horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingM)
                ) {
                    HomeExpanded()
                }
            },
            compactContent = {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(GuardianTheme.dimens.spacingM)
                ) {
                    HomeCompact()
                }
            }
        )
    }
}

@Preview(
    device = "spec:width=1280dp,height=800dp,dpi=240,orientation=portrait",
    showBackground = true, showSystemUi = false
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}