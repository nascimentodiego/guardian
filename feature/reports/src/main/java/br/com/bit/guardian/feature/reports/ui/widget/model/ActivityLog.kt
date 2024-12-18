package br.com.bit.guardian.feature.reports.ui.widget.model


data class ActivityLog(
    val date: String,
    val time: String,
    val deviceName: String,
    val deviceType: DeviceType,
    val action: String
)

enum class DeviceType {
    BIT_GARAGE, BIT_PIR, BIT_CAM, UNKNOWN
}
