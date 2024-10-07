package nl.jaysh.football.core.testing.data

import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.model.matches.Winner
import java.time.LocalDateTime

object MatchesTestData {
    val matches = listOf(
        Match(
            id = 441568,
            date = LocalDateTime.of(2023, 10, 29, 11, 15, 0, 0),
            matchDay = 10,
            homeTeam = TeamTestData.fcTwente,
            homeScore = 2,
            awayTeam = TeamTestData.feyenoord,
            awayScore = 1,
            winner = Winner.HOME_TEAM,
        ),
        Match(
            id = 441604,
            date = LocalDateTime.of(2023, 12, 3, 11, 15, 0, 0),
            matchDay = 14,
            homeTeam = TeamTestData.psv,
            homeScore = 1,
            awayTeam = TeamTestData.feyenoord,
            awayScore = 2,
            winner = Winner.AWAY_TEAM,
        ),
    )
}