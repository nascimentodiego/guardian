package br.com.bit.guardian.datasource.serializers

import br.com.bit.guardian.datasource.remote.response.report.DeviceTypeResponse
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DeviceTypeSerializer : KSerializer<DeviceTypeResponse> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            serialName = "DeviceType",
            kind = PrimitiveKind.STRING
        )

    override fun deserialize(decoder: Decoder): DeviceTypeResponse {
        return runCatching {
            DeviceTypeResponse.valueOf(decoder.decodeString())
        }.getOrElse { DeviceTypeResponse.UNKNOWN }
    }

    override fun serialize(encoder: Encoder, value: DeviceTypeResponse) {
        encoder.encodeString(value.name)
    }
}