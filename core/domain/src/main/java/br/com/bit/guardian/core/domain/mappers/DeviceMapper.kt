package br.com.bit.guardian.core.domain.mappers

import br.com.bit.guardian.core.data.repository.model.device.DeviceTypeData
import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import br.com.bit.guardian.core.domain.entities.DeviceType

fun ReportItemData.toReportItem(): DeviceReportItem {
    return DeviceReportItem(
        date = this.date,
        deviceType = this.deviceType.toDeviceType(),
        userEmail = this.userEmail
    )
}

fun DeviceTypeData.toDeviceType(): DeviceType {
    return runCatching {
        DeviceType.valueOf(this.name)
    }.getOrElse { DeviceType.UNKNOWN }
}