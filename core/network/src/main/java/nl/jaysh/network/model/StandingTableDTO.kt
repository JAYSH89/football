package nl.jaysh.network.model

import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.standing.Standing
import nl.jaysh.football.core.domain.model.standing.StandingStatistic

@Serializable
data class StandingTableDTO(
    val position: Int,
    val team: TeamDTO,
    val playedGames: Int,
    val won: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int,
)

fun StandingTableDTO.toStanding(): Standing = Standing(
    position = position,
    team = team.toTeam(),
    statistic = StandingStatistic(
        playedGames = playedGames,
        won = won,
        draw = playedGames - won - lost,
        lost = lost,
        points = points,
        goalsFor = goalsFor,
        goalsAgainst = goalsAgainst,
        goalDifference = goalDifference,
    ),
)
