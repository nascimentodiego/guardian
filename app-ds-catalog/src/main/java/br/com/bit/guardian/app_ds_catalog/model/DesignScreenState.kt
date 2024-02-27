package br.com.bit.guardian.app_ds_catalog.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import br.com.bit.guardian.app_ds_catalog.R
import br.com.bit.guardian.app_ds_catalog.ui.ColorTokenScreen
import br.com.bit.guardian.app_ds_catalog.ui.DimensTokenScreen
import br.com.bit.guardian.app_ds_catalog.ui.TypographyTokenScreen

data class Tab(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val screen: @Composable () -> Unit
)


val tabs = mutableListOf(
    Tab(
        R.string.tab_title_color,
        R.drawable.baseline_color_lens_24
    ) {
        ColorTokenScreen()
    },
    Tab(R.string.tab_title_typography, R.drawable.baseline_font_download_24) {
        TypographyTokenScreen()
    },
    Tab(R.string.tab_title_dimens, R.drawable.baseline_photo_size_select_small_24) {
        DimensTokenScreen()
    },
    Tab(R.string.tab_title_components, R.drawable.baseline_dashboard_customize_24) {
        ColorTokenScreen()
    },
)