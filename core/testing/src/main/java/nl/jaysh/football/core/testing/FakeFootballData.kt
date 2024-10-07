package nl.jaysh.football.core.testing

import nl.jaysh.network.model.CompetitionDTO
import nl.jaysh.network.model.StandingDTO
import nl.jaysh.network.model.StandingTableDTO
import nl.jaysh.network.model.TeamDTO

object FakeFootballData {
    val psv = TeamDTO(
        id = 674,
        name = "PSV",
        shortName = "PSV",
        tla = "PSV",
        crest = "https://crests.football-data.org/674.png",
    )

    val feyenoord = TeamDTO(
        id = 675,
        name = "Feyenoord Rotterdam",
        shortName = "Feyenoord",
        tla = "FEY",
        crest = "https://crests.football-data.org/675.png",
    )

    val fcTwente = TeamDTO(
        id = 666,
        name = "FC Twente '65'",
        shortName = "Twente",
        tla = "TWE",
        crest = "https://crests.football-data.org/666.png",
    )

    val standings = listOf(
        StandingDTO(
            table = listOf(
                StandingTableDTO(
                    position = 1,
                    team = psv,
                    playedGames = 34,
                    won = 29,
                    lost = 1,
                    points = 91,
                    goalsFor = 111,
                    goalsAgainst = 21,
                    goalDifference = 90,
                )
            ),
        ),
        StandingDTO(
            table = listOf(
                StandingTableDTO(
                    position = 2,
                    team = feyenoord,
                    playedGames = 34,
                    won = 26,
                    lost = 2,
                    points = 84,
                    goalsFor = 92,
                    goalsAgainst = 26,
                    goalDifference = 66,
                )
            )
        ),
        StandingDTO(
            table = listOf(
                StandingTableDTO(
                    position = 3,
                    team = fcTwente,
                    playedGames = 34,
                    won = 21,
                    lost = 7,
                    points = 69,
                    goalsFor = 69,
                    goalsAgainst = 36,
                    goalDifference = 33,
                )
            )
        ),
    )

    val eredivisie = CompetitionDTO(
        id = 2163,
        name = "Eredivisie",
        type = "LEAGUE",
        emblem = "https://crests.football-data.org/ED.png",
    )

    val primeraDivision = CompetitionDTO(
        id = 2014,
        name = "Primera Division",
        type = "LEAGUE",
        emblem = "https://crests.football-data.org/laliga.png",
    )

    val worldCup = CompetitionDTO(
        id = 2014,
        name = "FIFA World Cup",
        type = "CUP",
        emblem = "https://crests.football-data.org/qatar.png",
    )
}