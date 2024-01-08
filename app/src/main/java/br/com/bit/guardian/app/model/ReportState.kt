package br.com.bit.guardian.app.model

import br.com.bit.guardian.core.common.formatters.PATTERN_DATE_BR_2
import br.com.bit.guardian.core.common.formatters.toDateTimeFormat
import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse

data class ReportState(
    val date: String,
    val device: String,
    val user: String
) {
    constructor(response: ReportItemResponse) : this(
        date = response.date.toDateTimeFormat(PATTERN_DATE_BR_2),
        device = response.deviceType.name,
        user = response.userEmail
    )
}
