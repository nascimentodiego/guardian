package br.com.bit.guardian.app.model

import br.com.bit.guardian.datasource.remote.response.report.ReportItemResponse

data class ReportState(
    val date: String,
    val device: String,
    val user: String
) {
    constructor(response: ReportItemResponse) : this(
        date = response.date,
        device = response.deviceType,
        user = response.userEmail
    )
}
