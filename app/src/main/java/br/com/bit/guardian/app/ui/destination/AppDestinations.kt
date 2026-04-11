package br.com.bit.guardian.app.ui.destination

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import br.com.bit.guardian.app.R
import br.com.bit.guardian.core.designsystem.icon.GuardianIcon

enum class AppDestinations(
    @StringRes val label: Int,
    @DrawableRes val icon: Int,
    @StringRes val contentDescription: Int = label
) {
    HOME(R.string.menu_home, GuardianIcon.Home, R.string.menu_home),
    DEVICES(R.string.menu_devices, GuardianIcon.Devices, R.string.menu_devices),
    ACTIVITIES(R.string.menu_logs, GuardianIcon.Reports, R.string.menu_logs),
    SETTINGS(R.string.menu_settings, GuardianIcon.Settings, R.string.menu_settings)
}