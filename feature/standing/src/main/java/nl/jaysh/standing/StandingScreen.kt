package nl.jaysh.standing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import nl.jaysh.designsystem.theme.FootballTheme
import nl.jaysh.football.core.domain.model.Team
import nl.jaysh.football.core.domain.model.standing.Standing
import nl.jaysh.ui.FullScreenError
import nl.jaysh.ui.FullScreenLoader

@Preview
@Composable
private fun StandingScreenSuccessPreview() = FootballTheme {
    StandingContent(
        name = "Competition Name",
        standings = emptyList(),
        onSelectTeam = {}
    )
}

@Composable
fun StandingScreen(
    viewModel: StandingViewModel = hiltViewModel(),
    onSelectTeam: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.fetchStandingStatus) {
        FetchStandingStatus.Loading -> FullScreenLoader(
            message = "Fetching latest competition standings",
        )

        FetchStandingStatus.Error -> FullScreenError(
            message = "There was an error in retrieving the latest standings",
        )

        is FetchStandingStatus.Success -> StandingContent(
            name = state.standings?.competition?.name ?: "-",
            standings = state.standings?.standings ?: emptyList(),
            onSelectTeam = { team -> onSelectTeam(team.id) },
        )
    }
}

@Composable
private fun StandingContent(
    modifier: Modifier = Modifier,
    name: String,
    standings: List<Standing>,
    onSelectTeam: (Team) -> Unit,
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = name,
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            item {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    item {
                        Legend()
                    }

                    items(standings) { standing ->
                        FootballClub(standing = standing, onSelectTeam = onSelectTeam)
                    }
                }
            }
        }
    }
}

@Composable
private fun Legend(modifier: Modifier = Modifier) {
    StandingRow(
        modifier = modifier,
        position = "#",
        statistics = listOf("MP", "W", "D", "L", "PT", "GF", "GA", "GD"),
    )
}

@Composable
private fun FootballClub(
    modifier: Modifier = Modifier,
    standing: Standing,
    onSelectTeam: (Team) -> Unit,
) {
    StandingRow(
        modifier = modifier,
        position = "${standing.position}",
        team = standing.team.shortName,
        icon = { FootballClubLogo(crest = standing.team.crest) },
        statistics = listOf(
            "${standing.statistic.playedGames}",
            "${standing.statistic.won}",
            "${standing.statistic.draw}",
            "${standing.statistic.lost}",
            "${standing.statistic.points}",
            "${standing.statistic.goalsFor}",
            "${standing.statistic.goalsAgainst}",
            "${standing.statistic.goalDifference}",
        ),
        onClick = {
            onSelectTeam(standing.team)
        },
    )
}

@Composable
private fun FootballClubLogo(modifier: Modifier = Modifier, crest: String) {
    AsyncImage(
        modifier = modifier.size(size = 48.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(crest)
            .crossfade(enable = true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
