package br.com.bit.guardian.app_ds_catalog.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun TypographyTokenScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(text = "typography.titleMedium (Title)",
            style = GuardianTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        Text(text = "typography.titleSmall (Title Small)",
            style = GuardianTheme.typography.titleSmall)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        Text(text = "typography.labelMedium (Subtitle)",
            style = GuardianTheme.typography.labelMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        Text(text = "typography.headlineMedium (Paragraph Bold)",
            style = GuardianTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        Text(text = "typography.bodyMedium (Paragraph)",
            style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        Text(text = "typography.bodySmall (Paragraph Small)",
            style = GuardianTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

    }
}