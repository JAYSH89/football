package nl.jaysh.network.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateSerializer : KSerializer<LocalDate> {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): LocalDate {
        val value = decoder.decodeString()
        return LocalDate.parse(value, formatter)
    }

    override fun serialize(encoder: Encoder, value: LocalDate) {
        val formattedDate = value.format(formatter)
        encoder.encodeString(formattedDate)
    }
}