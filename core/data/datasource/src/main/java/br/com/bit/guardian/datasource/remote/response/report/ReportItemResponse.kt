package br.com.bit.guardian.datasource.remote.response.report

import br.com.bit.guardian.core.network.serializers.InstantSerializer
import br.com.bit.guardian.datasource.serializers.DeviceTypeSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReportItemResponse(
    @SerialName("date")
    @Serializable(InstantSerializer::class)
    val date:Instant,
    @SerialName("device")
    @Serializable(with = DeviceTypeSerializer::class)
    val deviceType:DeviceTypeResponse,
    @SerialName("user") val userEmail:String
)

/*

{
    "date": "05/01/2024 19:30:10",
    "device": "BIT_GARAGE",
    "user": "nascimento.diegof@gmail.com"
}
*/
