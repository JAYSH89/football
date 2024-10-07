package nl.jaysh.football.core.domain.model.competition

data class Competition(
    val id: Long,
    val name: String,
    val type: CompetitionType? = null,
    val emblem: String,
)
