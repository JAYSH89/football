package nl.jaysh.team

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nl.jaysh.designsystem.theme.FootballTheme
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.ui.FullScreenError
import nl.jaysh.ui.FullScreenLoader

@Preview
@Composable
private fun MatchesScreenPreview() = FootballTheme {
    MatchesScreenContent(matches = emptyList())
}

@Composable
fun MatchesScreen(viewModel: MatchesViewModel = hiltViewModel()) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        MatchesViewModelState.Loading -> FullScreenLoader(
            message = "Fetching match history!",
        )

        MatchesViewModelState.Error -> FullScreenError(
            message = "There was an error in retrieving the latest match history",
        )

        is MatchesViewModelState.Success -> MatchesScreenContent(matches = currentState.matches)
    }
}

@Composable
private fun MatchesScreenContent(
    modifier: Modifier = Modifier,
    matches: List<Match>,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) {
        item {
            Text(
                text = "Wedstrijden",
                style = MaterialTheme.typography.headlineLarge,
            )
        }

        items(matches) { match ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = match.homeTeam.shortName,
                    style = MaterialTheme.typography.bodySmall,
                )
                Row(
                    modifier = Modifier.width(64.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 12.dp,
                        alignment = Alignment.CenterHorizontally,
                    ),
                    content = {
                        val homeScore = match.homeScore?.toString() ?: "-"
                        Text(text = homeScore, style = MaterialTheme.typography.bodySmall)

                        val awayScore = match.awayScore?.toString() ?: "-"
                        Text(text = awayScore, style = MaterialTheme.typography.bodySmall)
                    }
                )
                Text(
                    modifier = Modifier.width(100.dp),
                    text = match.awayTeam.shortName,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End,
                )
            }
        }
    }
}
