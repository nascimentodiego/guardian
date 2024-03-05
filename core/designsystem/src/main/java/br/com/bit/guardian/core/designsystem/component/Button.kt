package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = GuardianTheme.colors.primary,
        ),
        contentPadding = contentPadding,
        content = content,
    )
}

@Composable
fun LoadedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = GuardianTheme.colors.primary,
        ),
        contentPadding = contentPadding
    ) {
        if (isLoading) {
            Row {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(2.dp)
                        .size(16.dp),
                    color = GuardianTheme.colors.onPrimary,
                    strokeWidth = 2.dp,
                    trackColor = GuardianTheme.colors.primary
                )
            }
            return@Button
        }

        content.invoke(this)
    }
}

@Composable
fun SimpleButton(
    modifier: Modifier = Modifier,
    @StringRes titleRes: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding
) {
    val borderColor = if (enabled)
        GuardianTheme.colors.onSecondary
    else
        GuardianTheme.colors.onSecondary.copy(alpha = 0.1f)
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        border = BorderStroke(1.dp, borderColor),
        colors = ButtonDefaults.buttonColors(
            containerColor = GuardianTheme.colors.background,
            contentColor = GuardianTheme.colors.onBackground,
            disabledContainerColor = GuardianTheme.colors.background.copy(alpha = 0.9f),
            disabledContentColor = GuardianTheme.colors.onBackground.copy(alpha = 0.1f)
        ),
        contentPadding = contentPadding
    ) {
        Text(
            text = stringResource(id = titleRes),
            style = GuardianTheme.typography.labelSmall,
            color = GuardianTheme.colors.textTitle
        )
    }
}

@Composable
fun FinishButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = GuardianTheme.colors.onBackground,
            disabledContainerColor = Color.Transparent
        ),
        contentPadding = contentPadding
    ) {
        content.invoke(this)
        Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingS))
        Icon(
            modifier = Modifier.background(
                color = GuardianTheme.colors.surface,
                shape = CircleShape
            ),
            painter = painterResource(id = R.drawable.ds_ic_check_circle),
            contentDescription = null,
            tint = GuardianTheme.colors.success
        )
    }
}

@Composable
fun NextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = GuardianTheme.colors.onBackground,
            disabledContainerColor = Color.Transparent
        ),
        contentPadding = contentPadding
    ) {
        content.invoke(this)
        Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingS))
        Icon(
            modifier = Modifier.background(
                color = GuardianTheme.colors.surface,
                shape = CircleShape
            ),
            painter = painterResource(id = R.drawable.ds_ic_chevron_right),
            contentDescription = null,
            tint = GuardianTheme.colors.primary
        )
    }
}

@Composable
fun PrevButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = GuardianTheme.colors.onBackground,
            disabledContainerColor = Color.Transparent
        ),
        contentPadding = contentPadding
    ) {
        Icon(
            modifier = Modifier.background(
                color = GuardianTheme.colors.surface,
                shape = CircleShape
            ),
            painter = painterResource(id = R.drawable.ds_ic_chevron_left),
            contentDescription = null,
            tint = GuardianTheme.colors.primary
        )
        Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingS))
        content.invoke(this)
    }
}

@ThemePreviews
@Composable
fun SimpleButtonPreview() {
    GuardianTheme {
        SimpleButton(
            titleRes = R.string.ds_button_try_again,
            onClick = {}
        )
    }
}


@ThemePreviews
@Composable
fun FinishButtonPreview() {
    GuardianTheme {
        FinishButton(
            onClick = {}
        ) {
            Text(text = "Success")
        }
    }
}


@Composable
fun PrevButtonPreview() {
    GuardianTheme {
        PrevButton(
            modifier = Modifier.width(200.dp),
            onClick = {}
        ) {
            Text(text = "Prev")
        }
    }
}


@Composable
fun NextButtonPreview() {
    GuardianTheme {
        NextButton(
            modifier = Modifier.width(200.dp),
            onClick = {}
        ) {
            Text(text = "Next")
        }
    }
}

@Composable
fun LoadedButtonPreview() {
    GuardianTheme {
        LoadedButton(
            modifier = Modifier.width(200.dp),
            isLoading = true,
            onClick = {}
        ) {
            Text(text = "Send")
        }
    }
}


