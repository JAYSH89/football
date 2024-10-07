package nl.jaysh.football.core.testing.data

import nl.jaysh.football.core.domain.model.standing.CompetitionStanding
import nl.jaysh.football.core.domain.model.standing.Standing
import nl.jaysh.football.core.domain.model.standing.StandingStatistic

object StandingTestData {
    val standing = listOf(
        Standing(
            position = 1,
            team = TeamTestData.psv,
            statistic = StandingStatistic(
                playedGames = 34,
                won = 29,
                draw = 4,
                lost = 1,
                points = 91,
                goalsFor = 111,
                goalsAgainst = 21,
                goalDifference = 90,
            )
        ),
        Standing(
            position = 2,
            team = TeamTestData.feyenoord,
            statistic = StandingStatistic(
                playedGames = 34,
                won = 26,
                draw = 6,
                lost = 2,
                points = 84,
                goalsFor = 92,
                goalsAgainst = 26,
                goalDifference = 66,
            )
        ),
        Standing(
            position = 3,
            team = TeamTestData.fcTwente,
            statistic = StandingStatistic(
                playedGames = 34,
                won = 21,
                draw = 6,
                lost = 7,
                points = 69,
                goalsFor = 69,
                goalsAgainst = 36,
                goalDifference = 33,
            )
        )
    )

    val competitionStanding = CompetitionStanding(
        competition = CompetitionTestData.eredivisie,
        standings = standing,
    )
}