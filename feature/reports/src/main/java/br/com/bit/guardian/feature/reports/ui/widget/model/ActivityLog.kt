package br.com.bit.guardian.feature.reports.ui.widget.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityLog(
    val key: String,
    val date: String,
    val time: String,
    val deviceName: String,
    val deviceType: DeviceType,
    val action: DeviceAction
) : Parcelable {
    companion object {
        fun empty() = ActivityLog("-1", "--", "--", "Dispositivo", DeviceType.UNKNOWN, DeviceAction.UNKNOW)
    }
}

@Stable
@Parcelize
data class ListStableOfString(val list: List<String> = emptyList()) : Parcelable

enum class DeviceType {
    BIT_GARAGE, BIT_PIR, BIT_CAM, UNKNOWN
}
