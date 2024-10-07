package nl.jaysh.network.model

import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding

@Serializable
data class StandingResponseDTO(
    val standings: List<StandingDTO>,
    val competition: CompetitionDTO,
)

fun StandingResponseDTO.toCompetitionStanding(): CompetitionStanding {
    val tables = standings.flatMap { standing -> standing.table }
    val standings = tables.map { dto -> dto.toStanding() }

    return CompetitionStanding(
        competition = competition.toCompetition(),
        standings = standings,
    )
}

@Serializable
data class StandingDTO(
    val table: List<StandingTableDTO>,
)
