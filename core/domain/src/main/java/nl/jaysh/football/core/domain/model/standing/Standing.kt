package nl.jaysh.football.core.domain.model.standing

import nl.jaysh.football.core.domain.model.Team

data class Standing(
    val position: Int,
    val team: Team,
    val statistic: StandingStatistic,
)
