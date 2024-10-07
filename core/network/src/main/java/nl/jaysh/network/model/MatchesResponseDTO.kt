package nl.jaysh.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.network.serializers.UtcDateSerializer
import java.time.LocalDateTime

@Serializable
data class MatchesResponseDTO(
    val matches: List<MatchesDTO>,
)

@Serializable
data class MatchesDTO(
    val id: Long,
    val matchDay: Int? = null,
    val homeTeam: TeamDTO,
    val awayTeam: TeamDTO,
    val score: ScoreDTO,
    @SerialName("utcDate") @Serializable(with = UtcDateSerializer::class) val date: LocalDateTime,
)

fun MatchesDTO.toMatch() = Match(
    id = id,
    date = date,
    matchDay = matchDay,
    homeTeam = homeTeam.toTeam(),
    homeScore = score.fullTime.home,
    awayTeam = awayTeam.toTeam(),
    awayScore = score.fullTime.away,
    winner = score.winner,
)
