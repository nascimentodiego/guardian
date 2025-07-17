package br.com.bit.guardian.settings.ui.profile.composable.components

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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import br.com.bit.guardian.core.designsystem.extension.GuardianThemePreviews
import br.com.bit.guardian.core.designsystem.extension.conditional
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon
import br.com.bit.guardian.core.designsystem.theme.GuardianTheme

@Composable
fun AvatarSelectableItem(
    modifier: Modifier = Modifier,
    avatar: Int,
    isSelected: Boolean,
    onSelectClick: (avatar: Int) -> Unit
) {
    val avatarResource = GuardianIcon.getUserAvatar(avatar)

    Box(
        modifier = modifier
            .size(80.dp)
            .clickable { onSelectClick.invoke(avatar) },
        contentAlignment = Alignment.BottomEnd
    ) {
        val borderWidth = 4.dp
        Image(
            painter = painterResource(id = avatarResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .conditional(isSelected) {
                    border(
                        BorderStroke(borderWidth, GuardianTheme.colors.success),
                        CircleShape
                    )
                }
                .clip(CircleShape)
        )
        if (isSelected) {
            Icon(
                imageVector = ImageVector.vectorResource(GuardianIcon.CheckCircle),
                contentDescription = null,
                modifier = Modifier
                    .background(
                        GuardianTheme.colors.success,
                        CircleShape
                    )
                    .size(24.dp)
                    .padding(4.dp),
                tint = GuardianTheme.colors.white
            )
        }
    }
}

@GuardianThemePreviews
@Composable
fun AvatarSelectableItemPreview() {
    GuardianTheme {
        Column(modifier = Modifier) {
            AvatarSelectableItem(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                avatar = 1,
                isSelected = true,
                onSelectClick = { }
            )
        }
    }
}