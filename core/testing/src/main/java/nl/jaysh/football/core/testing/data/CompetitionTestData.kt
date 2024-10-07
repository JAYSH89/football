package nl.jaysh.football.core.testing.data

import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.competition.CompetitionType

object CompetitionTestData {
    val eredivisie = Competition(
        id = 2163,
        name = "Eredivisie",
        type = CompetitionType.LEAGUE,
        emblem = "https://crests.football-data.org/ED.png",
    )

    val primeraDivision = Competition(
        id = 2014,
        name = "Primera Division",
        type = CompetitionType.LEAGUE,
        emblem = "https://crests.football-data.org/laliga.png",
    )

    val worldCup = Competition(
        id = 2014,
        name = "FIFA World Cup",
        type = CompetitionType.CUP,
        emblem = "https://crests.football-data.org/qatar.png",
    )
}