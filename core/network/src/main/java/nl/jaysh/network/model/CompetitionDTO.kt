package nl.jaysh.network.model

import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.competition.CompetitionType

@Serializable
data class CompetitionDTO(
    val id: Long,
    val name: String,
    val type: String,
    val emblem: String,
)

fun CompetitionDTO.toCompetition(): Competition = Competition(
    id = id,
    name = name,
    type = CompetitionType.valueOfOrNull(name = type),
    emblem = emblem,
)
