package br.com.bit.guardian.feature.reports.domain.mappers

import br.com.bit.guardian.core.common.formatters.PATTERN_DAY_MONTH_BR
import br.com.bit.guardian.core.common.formatters.PATTERN_TIME_BR_1
import br.com.bit.guardian.core.common.formatters.formatDateTime
import br.com.bit.guardian.feature.reports.data.repository.model.Report
import br.com.bit.guardian.feature.reports.domain.entities.ReportEntity

fun Report.toEntity() = ReportEntity(
    deviceName = this.deviceName,
    date = this.dateTime.formatDateTime(PATTERN_DAY_MONTH_BR),
    time = this.dateTime.formatDateTime(PATTERN_TIME_BR_1),
    action = this.action
)