package br.com.bit.guardian.core.network.serializers

import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object InstantSerializer: KSerializer<Instant> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Instant",
        kind = PrimitiveKind.STRING
    )

    override fun deserialize(decoder: Decoder) = decoder.decodeString().toInstant()

    override fun serialize(encoder: Encoder, value: Instant) {
        encoder.encodeString(value.toString())
    }
}