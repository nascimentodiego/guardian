package br.com.bit.guardian.settings.ui.profile.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextBodySmall
import br.com.bit.guardian.core.designsystem.component.TextTitleMedium
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.settings.R

@Composable
fun NickNameField(
    modifier: Modifier = Modifier,
    label: Int,
    value: String,
    onClick: () -> Unit
) = Row(
    modifier = modifier.clickable { onClick.invoke() },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .weight(0.9f)
    ) {
        TextBodySmall(label)
        TextTitleMedium(value)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(0.1f),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(GuardianIcon.ChevronRight),
            contentDescription = null,
            modifier = Modifier
                .background(
                    GuardianTheme.colors.iconActiveColor,
                    CircleShape
                )
                .size(24.dp)
                .padding(4.dp),
            tint = GuardianTheme.colors.onPrimary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NickNameFieldPreview() {
    GuardianTheme {
        NickNameField(
            label = R.string.settings_nickname_label,
            value = "figue",
            onClick = { }
        )
    }
}