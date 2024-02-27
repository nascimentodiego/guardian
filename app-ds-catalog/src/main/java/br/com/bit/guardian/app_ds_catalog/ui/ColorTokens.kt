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
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun ColorTokenScreen() {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        // colors.onPrimary
        Text(text = "GuardianTheme.colors.onPrimary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onPrimary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.primary
        Text(text = "GuardianTheme.colors.onPrimary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.primary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.onPrimaryContainer
        Text(
            text = "GuardianTheme.colors.onPrimaryContainer",
            style = GuardianTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onPrimaryContainer,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.onSecondary
        Text(text = "GuardianTheme.colors.onSecondary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onSecondary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.secondary
        Text(text = "GuardianTheme.colors.secondary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.secondary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.onTertiary
        Text(text = "GuardianTheme.colors.onTertiary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onTertiary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.tertiary
        Text(text = "GuardianTheme.colors.tertiary", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.tertiary,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.onBackground
        Text(
            text = "GuardianTheme.colors.onBackground",
            style = GuardianTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onBackground,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.background
        Text(text = "GuardianTheme.colors.background", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.background,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.onSurface
        Text(text = "GuardianTheme.colors.onSurface", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.onSurface,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.surface
        Text(text = "GuardianTheme.colors.surface", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.surface,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        // colors.success
        Text(text = "GuardianTheme.colors.success", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.success,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        //////////////////////////////

        // colors.warning
        Text(text = "GuardianTheme.colors.warning", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.warning,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////

        //////////////////////////////

        // colors.error
        Text(text = "GuardianTheme.colors.error", style = GuardianTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .background(
                    color = GuardianTheme.colors.error,
                    shape = CircleShape
                )
        ) {

        }
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingS))

        //////////////////////////////
    }
}

@ThemePreviews
@Composable
fun ColorTokenScreenPreview() {
    GuardianTheme {
        ColorTokenScreen()
    }
}