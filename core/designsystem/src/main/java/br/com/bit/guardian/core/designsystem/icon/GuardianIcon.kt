package br.com.bit.guardian.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Edit
import br.com.bit.guardian.core.designsystem.R

@Suppress("MemberNameEqualsClassName")
object GuardianIcon {

    val GuardianIcon = R.drawable.ds_ic_guardian

    // General
    val ArrowForward = Icons.AutoMirrored.Rounded.ArrowForward
    val ArrowBack = Icons.AutoMirrored.Rounded.ArrowBack
    val ChevronRight = R.drawable.ds_ic_chevron_right
    val ChevronLeft = R.drawable.ds_ic_chevron_left
    val Close = R.drawable.ds_ic_close
    val Done = R.drawable.ds_ic_close
    val Edit = Icons.Rounded.Edit
    val Logout = R.drawable.ds_ic_logout
    val CheckCircle = R.drawable.ds_ic_check_circle
    val UnCheckCircle = R.drawable.ds_ic_radio_button_unchecked
    val Visibility = R.drawable.ds_ic_visibility
    val VisibilityOff = R.drawable.ds_ic_visibility_off
    val AddUser = R.drawable.ds_ic_person_add
    val Calendar = R.drawable.ds_ic_calendar
    val Timer = R.drawable.ds_ic_timer
    val Empty = R.drawable.ds_ic_empty_dashboar

    // Menu
    val Home = R.drawable.ds_ic_home
    val Devices = R.drawable.ds_ic_device
    val Reports = R.drawable.ds_ic_reports
    val Settings = R.drawable.ds_ic_settings
    val Phone = R.drawable.ds_ic_phone

    // Wifi
    val WifiManege = R.drawable.ds_ic_wifi_manage
    val WifiBar1 = R.drawable.ds_ic_wifi_1_bar
    val WifiBar2 = R.drawable.ds_ic_wifi_2_bar
    val WifiBar3 = R.drawable.ds_ic_wifi_3_bar
    val WifiBar4 = R.drawable.ds_ic_wifi_4_bar

    // RF Control
    val RFRemoteControl = R.drawable.ds_ic_rf_remote

    // Avatar
    val Avatar01 = R.drawable.ds_ic_avatar_01
    val Avatar02 = R.drawable.ds_ic_avatar_02
    val Avatar03 = R.drawable.ds_ic_avatar_03
    val Avatar04 = R.drawable.ds_ic_avatar_04
    val Avatar05 = R.drawable.ds_ic_avatar_05
    val Avatar06 = R.drawable.ds_ic_avatar_06

    val avatarIcons = hashMapOf(
        1 to Avatar01,
        2 to Avatar02,
        3 to Avatar03,
        4 to Avatar04,
        5 to Avatar05,
        6 to Avatar06
    )

    fun getUserAvatar(avatarId: Int = 1) = avatarIcons[avatarId] ?: Avatar01
}