package br.com.bit.guardian.core.data.repository.model.device

import kotlinx.datetime.Instant

data class ReportItemData(
    val date: Instant,
    val deviceType:DeviceTypeData,
    val userEmail:String
)