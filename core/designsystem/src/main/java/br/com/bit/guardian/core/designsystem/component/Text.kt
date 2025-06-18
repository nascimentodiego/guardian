package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
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
   title: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = title,
    modifier = modifier,
    style = GuardianTheme.typography.titleMedium,
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
fun TextTitleSmall(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = title,
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
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) = Text(
    text = stringResource(id = stringRes),
    modifier = modifier,
    style = GuardianTheme.typography.bodySmall,
    overflow = overflow,
    color = color,
    maxLines = maxLines,
    textAlign = textAlign
)

@Composable
fun TextBodySmall(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textBody,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE
) = Text(
    text = text,
    modifier = modifier,
    style = GuardianTheme.typography.bodySmall,
    overflow = overflow,
    color = color,
    maxLines = maxLines,
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
fun TextBodyMedium(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textBody,
    textAlign: TextAlign? = null
) = Text(
    text = text,
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

@Composable
fun TextHeadLineMedium(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null
) = Text(
    text = title,
    modifier = modifier,
    style = GuardianTheme.typography.headlineMedium,
    color = color,
    textAlign = textAlign
)

@Composable
fun TextHeadLineSmall(
    title: String,
    modifier: Modifier = Modifier,
    color: Color = GuardianTheme.colors.textTitle,
    textAlign: TextAlign? = null,
    maxLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip
) = Text(
    text = title,
    modifier = modifier,
    style = GuardianTheme.typography.headlineSmall,
    color = color,
    maxLines = maxLines,
    overflow = overflow,
    textAlign = textAlign
)

