package br.com.bit.guardian.feature.reports.ui.mappers

import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceType

fun ReportEntity.toActivityLog() = ActivityLog(
    date = this.dateTime,
    time = this.dateTime,
    action = this.action,
    deviceName = this.deviceName,
    deviceType = DeviceType.BIT_GARAGE
)