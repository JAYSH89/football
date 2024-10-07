package nl.jaysh.football.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import nl.jaysh.common.util.Constants.COMPETITION_ID_KEY
import nl.jaysh.common.util.Constants.TEAM_ID_KEY
import nl.jaysh.feature.competition.CompetitionScreen
import nl.jaysh.standing.StandingScreen
import nl.jaysh.team.MatchesScreen

@Composable
fun RootNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.COMPETITION) {

        composable(Destination.COMPETITION) {
            CompetitionScreen(
                onSelectCompetition = { competitionId ->
                    val path = Destination.standing(competitionId = competitionId)
                    navController.navigate(path)
                }
            )
        }

        composable(
            route = "${Destination.STANDING}?$COMPETITION_ID_KEY={$COMPETITION_ID_KEY}",
            arguments = listOf(navArgument(name = COMPETITION_ID_KEY) { type = NavType.LongType }),
            content = {
                StandingScreen(
                    onSelectTeam = { teamId ->
                        val path = Destination.matches(teamId = teamId)
                        navController.navigate(path)
                    }
                )
            }
        )

        composable(
            route = "${Destination.MATCHES}?$TEAM_ID_KEY={$TEAM_ID_KEY}",
            arguments = listOf(navArgument(name = TEAM_ID_KEY) { type = NavType.LongType }),
            content = { MatchesScreen() }
        )
    }
}