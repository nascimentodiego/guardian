package br.com.bit.guardian.app_ds_catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun ColorTokenScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Text(text = "GuardianTheme.colors.onPrimary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onPrimary
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        Text(text = "GuardianTheme.colors.primary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.primary
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))
    }
}