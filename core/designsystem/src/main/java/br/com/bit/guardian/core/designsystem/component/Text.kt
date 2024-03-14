package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun TextTitleLarge(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = titleRes),
    style = GuardianTheme.typography.titleLarge,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextTitleMedium(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = titleRes),
    modifier = modifier,
    style = GuardianTheme.typography.titleMedium,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextTitleSmall(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = titleRes),
    modifier = modifier,
    style = GuardianTheme.typography.titleSmall,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextBodySmall(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textBody,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = stringRes),
    modifier = modifier,
    style = GuardianTheme.typography.bodySmall,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextBodyMedium(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textBody,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = stringRes),
    modifier = modifier,
    style = GuardianTheme.typography.bodyMedium,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextBodyLarge(
    @StringRes stringRes: Int,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textBody,
    textAlign: TextAlign? = null
) = Text(
    text = stringResource(id = stringRes),
    modifier = modifier,
    style = GuardianTheme.typography.bodyLarge,
    color = color,
    textAlign = textAlign
)