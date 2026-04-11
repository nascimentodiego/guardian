package br.com.bit.guardian.feature.reports.data.datasource.remote.response

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
@Suppress("ConstructorParameterNaming")
data class ReportResponse(
    var date_time: String = "",
    var device: Device = Device(),
    var action: String? = ""
)