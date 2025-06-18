package br.com.bit.guardian.settings.ui.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.LoadingComponent
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun SettingsLoading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        LoadingComponent(roundSize = 75.dp, width = 150.dp, height = 150.dp)

        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXL))
        LoadingComponent(roundSize = 4.dp, height = 50.dp)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        LoadingComponent(roundSize = 4.dp, height = 50.dp)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        LoadingComponent(roundSize = 4.dp, height = 50.dp)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingM))
        LoadingComponent(roundSize = 4.dp, height = 50.dp)

        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXL))
        LoadingComponent(roundSize = 4.dp, height = 50.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsLoadingPreview() {
    GuardianTheme {
        SettingsLoading()
    }
}