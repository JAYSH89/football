package nl.jaysh.common.util

import java.time.LocalDateTime

object Constants {
    const val TEAM_ID_KEY: String = "teamId"
    const val COMPETITION_ID_KEY: String = "competitionId"
    const val PREFERENCES: String = "preferences_store"

    private const val MAX_SEASONS = 3
    fun getAvailableSeasons(): List<Int> = generateSequence(
        seed = LocalDateTime.now().year,
        nextFunction = { year -> year - 1 },
    ).takeWhile { year ->
        year >= LocalDateTime.now().year - Constants.MAX_SEASONS
    }.toList()
}