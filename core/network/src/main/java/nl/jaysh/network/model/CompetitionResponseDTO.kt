package nl.jaysh.network.model

import kotlinx.serialization.Serializable

@Serializable
data class CompetitionResponseDTO(
    val competitions: List<CompetitionDTO>
)
