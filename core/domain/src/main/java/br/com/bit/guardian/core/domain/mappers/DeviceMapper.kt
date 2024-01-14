package br.com.bit.guardian.core.domain.mappers

import br.com.bit.guardian.core.data.repository.model.device.DeviceTypeData
import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import br.com.bit.guardian.core.domain.entities.DeviceReportItem
import br.com.bit.guardian.core.domain.entities.DeviceType

fun ReportItemData.toReportItem(): DeviceReportItem {
    return DeviceReportItem(
        date = date,
        deviceType = deviceType.toDeviceType(),
        userEmail = userEmail
    )
}

fun DeviceTypeData.toDeviceType(): DeviceType {
    return runCatching {
        DeviceType.valueOf(name)
    }.getOrElse { DeviceType.UNKNOWN }
}