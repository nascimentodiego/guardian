package br.com.bit.guardian.feature.reports.ui.widget.model

import android.os.Parcelable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityLog(
    val date: String,
    val time: String,
    val deviceName: String,
    val deviceType: DeviceType,
    val action: String,
//    val list: ListStableOfString = ListStableOfString()
    val list: List<String> = emptyList()
) : Parcelable {
    companion object {
        fun empty() = ActivityLog("--", "--", "Dsipositivo", DeviceType.UNKNOWN, "(-)")
    }
}

@Stable
@Parcelize
data class ListStableOfString(val list: List<String> = emptyList()): Parcelable

enum class DeviceType {
    BIT_GARAGE, BIT_PIR, BIT_CAM, UNKNOWN
}
