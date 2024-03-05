package br.com.bit.guardian.core.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.R
import br.com.bit.guardian.core.designsystem.extension.ThemePreviews
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.core.designsystem.theme.LocalWindowSizeClass

@Composable
fun SimpleError(
    modifier: Modifier = Modifier,
    widthSizeClass: WindowWidthSizeClass? = null,
    @StringRes titleRes: Int? = R.string.ds_simple_error_title,
    @StringRes descriptionRes: Int? = R.string.ds_simple_error_description,
    @StringRes buttonLabelRes: Int = R.string.ds_button_try_again,
    onClickListener: () -> Unit
) {
    val widthSize = widthSizeClass ?: LocalWindowSizeClass.current.widthSizeClass
    val align = if (widthSize == WindowWidthSizeClass.Compact) {
        Alignment.CenterHorizontally
    } else {
        Alignment.End
    }

    Column(
        modifier = modifier
            .background(
                color = GuardianTheme.colors.background,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(GuardianTheme.dimens.spacingXS),
        horizontalAlignment = align
    ) {
        if (widthSize == WindowWidthSizeClass.Compact) {
            SimpleErrorCompact(
                titleRes,
                descriptionRes,
                buttonLabelRes,
            ) {
                onClickListener.invoke()
            }
        } else {
            SimpleErrorExpanded(
                titleRes,
                descriptionRes,
                buttonLabelRes,
            ) {
                onClickListener.invoke()
            }
        }
    }

}

@Composable
private fun SimpleErrorCompact(
    titleRes: Int?,
    descriptionRes: Int?,
    buttonLabelRes: Int,
    onClickListener: () -> Unit
) {
    Icon(
        painter = painterResource(id = R.drawable.ds_ic_error_48),
        tint = GuardianTheme.colors.error,
        contentDescription = null
    )

    titleRes?.let {
        Text(
            text = stringResource(id = it),
            style = GuardianTheme.typography.titleSmall,
            color = GuardianTheme.colors.textTitle
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXXS))
    }
    descriptionRes?.let {
        Text(
            text = stringResource(id = it),
            style = GuardianTheme.typography.bodySmall,
            color = GuardianTheme.colors.textBody
        )
        Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
    }

    SimpleButton(
        titleRes = buttonLabelRes,
        onClick = onClickListener
    )
}

@Composable
private fun SimpleErrorExpanded(
    titleRes: Int?,
    descriptionRes: Int?,
    buttonLabelRes: Int,
    onClickListener: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(72.dp),
            painter = painterResource(id = R.drawable.ds_ic_error_48),
            tint = GuardianTheme.colors.error,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Divider(
            modifier = Modifier
                .height(72.dp)
                .width(2.dp)
                .background(GuardianTheme.colors.error)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            titleRes?.let {
                Text(
                    text = stringResource(id = it),
                    style = GuardianTheme.typography.titleMedium,
                    color = GuardianTheme.colors.textTitle
                )
                Spacer(modifier = Modifier.width(GuardianTheme.dimens.spacingXXS))
            }
            descriptionRes?.let {
                Text(
                    text = stringResource(id = it),
                    style = GuardianTheme.typography.bodySmall,
                    color = GuardianTheme.colors.textBody
                )
                Spacer(modifier = Modifier.height(GuardianTheme.dimens.spacingXS))
            }
        }
    }

    SimpleButton(
        titleRes = buttonLabelRes,
        onClick = onClickListener
    )
}

@ThemePreviews
@Composable
fun SimpleErrorPreview() {
    GuardianTheme {
        SimpleError(
            widthSizeClass = WindowWidthSizeClass.Compact,
            titleRes = R.string.ds_simple_error_title,
            descriptionRes = R.string.ds_simple_error_description
        ) {

        }
    }
}