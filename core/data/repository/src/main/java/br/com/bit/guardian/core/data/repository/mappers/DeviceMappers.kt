package br.com.bit.guardian.core.data.repository.mappers

import br.com.bit.guardian.core.data.repository.model.device.DeviceTypeData
import br.com.bit.guardian.core.data.repository.model.device.ReportItemData
import br.com.bit.guardian.datasource.remote.response.report.DeviceTypeResponse
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse

fun ReportItemResponse.toReportItemModel(): ReportItemData {
    return ReportItemData(
        date = this.date,
        deviceType = this.deviceType.toDeviceType(),
        userEmail = this.userEmail
    )
}

fun DeviceTypeResponse.toDeviceType(): DeviceTypeData {
    return runCatching {
        DeviceTypeData.valueOf(this.name)
    }.getOrElse { DeviceTypeData.UNKNOWN }
}