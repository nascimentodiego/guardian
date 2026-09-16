package br.com.bit.guardian.feature.reports.ui.widget.model

import androidx.compose.runtime.Stable
import kotlinx.serialization.Serializable

@Serializable
data class ActivityLog(
    val key: String,
    val date: String,
    val time: String,
    val deviceName: String,
    val deviceType: DeviceType,
    val action: DeviceAction
) {
    companion object {
        fun empty() = ActivityLog(
            "-1",
            "--",
            "--",
            "Dispositivo",
            DeviceType.UNKNOWN,
            DeviceAction.UNKNOW
        )
    }
}

@Stable
@Serializable
data class ListStableOfString(val list: List<String> = emptyList())

@Serializable
enum class DeviceType {
    BIT_GARAGE, BIT_PIR, BIT_CAM, UNKNOWN
}
