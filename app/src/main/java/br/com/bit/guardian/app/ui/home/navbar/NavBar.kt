package br.com.bit.guardian.app.ui.home.navbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.app.ui.destination.AppDestinations
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.extension.GuardianThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun GuardianNavbar(
    modifier: Modifier = Modifier,
    initialDestination: AppDestinations = AppDestinations.HOME,
    entries: List<AppDestinations>,
    onSelectedItem: (destination: AppDestinations) -> Unit
) {
    var destinationSelected by remember { mutableStateOf(initialDestination) }
    val colors = arrayOf(
        0.0f to Color.Transparent,
        0.9f to GuardianTheme.colors.primary.copy(alpha = 0.1f),
        1f to GuardianTheme.colors.primary
    )
    Column {
        val brush = Brush.verticalGradient(colorStops = colors)
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
                .background(brush)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .background(color = GuardianTheme.colors.primaryContainer),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Items
            entries.forEach {
                NavbarItem(
                    destination = it,
                    isSelected = it == destinationSelected
                ) { dest ->
                    destinationSelected = dest
                    onSelectedItem(dest)
                }
            }
        }
    }
}

@Composable
fun NavbarItem(
    destination: AppDestinations,
    isSelected: Boolean,
    onSelectedItem: (destination: AppDestinations) -> Unit
) {
    val transition = updateTransition(isSelected, label = "item color animation")
    val itemContainerColor by transition.animateColor(
        transitionSpec = {
            tween(200)
        },
        label = "color"
    ) { state ->
        when (state) {
            true -> GuardianTheme.colors.iconActiveContainer
            false -> Color.Transparent
        }
    }

    val iconColor by transition.animateColor(
        transitionSpec = {
            tween(200)
        },
        label = "color"
    ) { state ->
        when (state) {
            true -> GuardianTheme.colors.iconActiveColor
            false -> GuardianTheme.colors.iconInactiveColor
        }
    }

    Box(
        modifier = Modifier
            .background(itemContainerColor, CircleShape)
            .widthIn(min = 48.dp)
            .heightIn(min = 32.dp)
            .clip(CircleShape)
            .clickable {
                if (!isSelected) {
                    onSelectedItem(destination)
                }
            }
            .padding(
                horizontal = GuardianTheme.dimens.spacingXS,
                vertical = GuardianTheme.dimens.spacingXXS
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = destination.icon),
                contentDescription = stringResource(id = destination.contentDescription),
                tint = iconColor
            )
            AnimatedVisibility(isSelected) {
                Row {
                    Spacer(
                        modifier = Modifier
                            .background(Color.Red)
                            .width(GuardianTheme.dimens.spacingXXS)
                    )
                    TextBodySmall(
                        modifier = Modifier,
                        stringRes = destination.label,
                        color = GuardianTheme.colors.iconActiveColor
                    )
                }
            }
        }
    }
}

@GuardianThemePreviews
@Composable
fun GuardianNavbarPreview() {
    GuardianTheme {
        GuardianNavbar(entries = AppDestinations.entries) {}
    }
}