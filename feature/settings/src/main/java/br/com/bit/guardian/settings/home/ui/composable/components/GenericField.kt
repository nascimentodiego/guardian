package br.com.bit.guardian.settings.home.ui.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.component.TextBodyMedium
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme
import br.com.bit.guardian.feature.settings.R

@Composable
fun GenericField(
    modifier: Modifier = Modifier,
    label: Int,
    onClick: () -> Unit
) = Row(
    modifier = modifier
        .defaultMinSize(minHeight = 50.dp)
        .clickable { onClick.invoke() },
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .weight(0.9f)
    ) {
        TextBodyMedium(label)
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
                .size(24.dp),
            tint = GuardianTheme.colors.iconActiveColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GenericFieldPreview() {
    GuardianTheme {
        GenericField(
            label = R.string.settings_phone_title,
            onClick = { }
        )
    }
}