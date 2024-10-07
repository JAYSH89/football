package nl.jaysh.football.core.domain.model.competition

enum class CompetitionType {
    LEAGUE, CUP;

    companion object {
        fun valueOfOrNull(name: String): CompetitionType? {
            return entries.firstOrNull { it.name == name }
        }
    }
}