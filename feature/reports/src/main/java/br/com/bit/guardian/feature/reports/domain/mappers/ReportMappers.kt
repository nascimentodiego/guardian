package br.com.bit.guardian.feature.reports.domain.mappers

import br.com.bit.guardian.feature.reports.data.repository.model.Report
import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity

fun Report.toEntity() = ReportEntity(
    deviceName = this.deviceName,
    dateTime = this.dateTime,
    action = this.action
)