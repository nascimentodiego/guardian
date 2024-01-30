package br.com.bit.guardian.core.designsystem.extension

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "dark",uiMode = Configuration.UI_MODE_NIGHT_YES)
annotation class ThemePreviews
