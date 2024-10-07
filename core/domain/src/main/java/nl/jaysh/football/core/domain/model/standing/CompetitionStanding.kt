package nl.jaysh.football.core.domain.model.standing

import nl.jaysh.football.core.domain.model.competition.Competition

data class CompetitionStanding(
    val competition: Competition,
    val standings: List<Standing>,
)
