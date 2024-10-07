package nl.jaysh.network.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import nl.jaysh.football.core.domain.model.matches.Winner

object WinnerSerializer : KSerializer<Winner> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(
        serialName = "Winner",
        kind = PrimitiveKind.STRING,
    )

    override fun serialize(encoder: Encoder, value: Winner) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): Winner {
        return Winner.valueOf(decoder.decodeString())
    }
}