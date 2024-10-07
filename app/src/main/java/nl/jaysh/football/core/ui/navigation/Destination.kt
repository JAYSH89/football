package nl.jaysh.football.core.ui.navigation

import nl.jaysh.common.util.Constants.COMPETITION_ID_KEY
import nl.jaysh.common.util.Constants.TEAM_ID_KEY

object Destination {
    const val COMPETITION = "competition"
    const val STANDING = "standing"
    const val MATCHES = "matches"

    fun standing(competitionId: Long): String = "$STANDING?$COMPETITION_ID_KEY=$competitionId"

    fun matches(teamId: Long): String = "$MATCHES?$TEAM_ID_KEY=$teamId"
}
