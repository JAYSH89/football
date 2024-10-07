package nl.jaysh.football.core.domain.model.matches

import nl.jaysh.football.core.domain.model.Team
import java.time.LocalDateTime

data class Match(
    val id: Long,
    val date: LocalDateTime,
    val matchDay: Int? = null,
    val homeTeam: Team,
    val homeScore: Int? = null,
    val awayTeam: Team,
    val awayScore: Int? = null,
    val winner: Winner? = null,
)
