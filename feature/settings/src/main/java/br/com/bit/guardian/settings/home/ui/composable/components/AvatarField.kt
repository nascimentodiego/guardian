package br.com.bit.guardian.settings.home.ui.composable.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun AvatarField(
    modifier: Modifier = Modifier,
    avatar: Int,
    onEditClick: () -> Unit
) {
    val avatarResource =GuardianIcon.getUserAvatar(avatar)

    Box(
        modifier = modifier
            .size(100.dp)
            .clickable { onEditClick.invoke() },
        contentAlignment = Alignment.BottomEnd
    ) {
        val borderWidth = 4.dp
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .border(
                    BorderStroke(borderWidth, GuardianTheme.colors.iconActiveColor),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )

        Icon(
            imageVector = GuardianIcon.Edit,
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
fun AvatarFieldPreview() {
    GuardianTheme {
        Column(modifier = Modifier.size(300.dp)) {
            AvatarField(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                avatar = 1,
                onEditClick = { }
            )
        }
    }
}