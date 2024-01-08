package br.com.bit.guardian.datasource.serializers

import br.com.bit.guardian.datasource.remote.response.report.DeviceType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object DeviceTypeSerializer : KSerializer<DeviceType> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor(
            serialName = "DeviceType",
            kind = PrimitiveKind.STRING
        )

    override fun deserialize(decoder: Decoder): DeviceType {
        return runCatching {
            DeviceType.valueOf(decoder.decodeString())
        }.getOrElse { DeviceType.UNKNOWN }
    }

    override fun serialize(encoder: Encoder, value: DeviceType) {
        encoder.encodeString(value.name)
    }
}