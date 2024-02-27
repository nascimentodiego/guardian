package br.com.bit.guardian.app_ds_catalog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun DimensTokenScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        // spacingXXS
        Text(
            text = "dimens.spacingXXS (4dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingXXS)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        // spacingXS
        Text(
            text = "dimens.spacingXS (8dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingXS)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////
        // spacingS
        Text(
            text = "dimens.spacingS (12.dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingS)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingM
        Text(
            text = "dimens.spacingM (16.dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingM)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingL
        Text(
            text = "dimens.spacingL (20.dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingL)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingXL
        Text(
            text = "dimens.spacingXL (24.dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingXL)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingXXL
        Text(
            text = "dimens.spacingXXL (32.dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingXXL)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingH
        Text(
            text = "dimens.spacingH (48dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingH)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////
        // spacingHX (52dp)
        Text(
            text = "dimens.spacingHX (52dp)",
            style = GuardianTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GuardianTheme.dimens.spacingHX)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        ////////////////////////////////////////////////////////////////////


    }
}

//val spacingHX: Dp = 52.dp
//val spacingH: Dp = 48.dp
//val spacingXXL: Dp = 32.dp
//val spacingXL: Dp = 24.dp
//val spacingL: Dp = 20.dp
//val spacingM: Dp = 16.dp
//val spacingS: Dp = 12.dp
//val spacingXS: Dp = 8.dp
//val spacingXXS: Dp = 4.dp


@ThemePreviews
@Composable
fun DimensScreenPreview() {
    GuardianTheme {
        DimensTokenScreen()
    }
}