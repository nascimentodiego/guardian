package br.com.bit.guardian.feature.reports.domain.entities

import br.com.bit.guardian.feature.reports.data.repository.model.Report

data class ReportEntity(val deviceName: String, val dateTime: String, val action: String) {
    constructor(report: Report) : this(
        deviceName = report.deviceName,
        dateTime = report.dateTime,
        action = report.action
    )
}
