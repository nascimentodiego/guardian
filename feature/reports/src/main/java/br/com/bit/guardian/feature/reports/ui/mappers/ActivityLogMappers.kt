package br.com.bit.guardian.feature.reports.ui.mappers

import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity
import br.com.bit.guardian.feature.reports.ui.widget.model.ActivityLog
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceAction
import br.com.bit.guardian.feature.reports.ui.widget.model.DeviceType
import java.util.UUID

fun ReportEntity.toActivityLog() = ActivityLog(
    key = UUID.randomUUID().toString(),
    date = this.date,
    time = this.time,
    action = DeviceAction.findByNameSafe(this.action),
    deviceName = this.deviceName,
    deviceType = DeviceType.BIT_GARAGE
)
