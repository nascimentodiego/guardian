package br.com.bit.guardian.core.domain.entities

import kotlinx.datetime.Instant

data class DeviceReportItem(
    val date: Instant,
    val deviceType: DeviceType,
    val userEmail: String
)