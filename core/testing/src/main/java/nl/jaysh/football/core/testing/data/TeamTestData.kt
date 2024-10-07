package nl.jaysh.football.core.testing.data

import nl.jaysh.football.core.domain.model.Team

object TeamTestData {
    val psv = Team(
        id = 674,
        name = "PSV",
        shortName = "PSV",
        crest = "https://crests.football-data.org/674.png",
    )

    val feyenoord = Team(
        id = 675,
        name = "Feyenoord Rotterdam",
        shortName = "Feyenoord",
        crest = "https://crests.football-data.org/675.png",
    )

    val fcTwente = Team(
        id = 666,
        name = "FC Twente '65'",
        shortName = "Twente",
        crest = "https://crests.football-data.org/666.png",
    )
}