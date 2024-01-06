package br.com.bit.guardian.datasource.remote.response.report

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportItemResponse(
    @SerialName("date") val date:String,
    @SerialName("device") val deviceType:String,
    @SerialName("user") val userEmail:String
)

/*

{
    "date": "05/01/2024 19:30:10",
    "device": "BIT_GARAGE",
    "user": "nascimento.diegof@gmail.com"
}
*/
