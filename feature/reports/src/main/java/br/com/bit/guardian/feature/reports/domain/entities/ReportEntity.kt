package br.com.bit.guardian.feature.reports.domain.entities

import br.com.bit.guardian.core.common.formatters.PATTERN_DAY_MONTH_BR
import br.com.bit.guardian.core.common.formatters.PATTERN_TIME_BR_1
import br.com.bit.guardian.core.common.formatters.formatDateTime
import br.com.bit.guardian.feature.reports.data.repository.model.Report

data class ReportEntity(
    val deviceName: String,
    val date: String,
    val time: String,
    val action: String
) {
    constructor(report: Report) : this(
        deviceName = report.deviceName,
        date = report.dateTime.formatDateTime(PATTERN_DAY_MONTH_BR),
        time = report.dateTime.formatDateTime(PATTERN_TIME_BR_1),
        action = report.action
    )
}