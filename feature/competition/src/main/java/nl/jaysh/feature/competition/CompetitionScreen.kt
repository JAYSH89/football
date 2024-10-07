package nl.jaysh.feature.competition

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import nl.jaysh.ui.FullScreenError
import nl.jaysh.ui.FullScreenLoader

@Composable
fun CompetitionScreen(
    viewModel: CompetitionViewModel = hiltViewModel(),
    onSelectCompetition: (Long) -> Unit,
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (state.fetchCompetitionStatus) {
        FetchCompetitionStatus.Loading -> FullScreenLoader(
            message = "Fetching competitions!",
        )

        FetchCompetitionStatus.Error -> FullScreenError(
            message = "There was an error in retrieving the latest competitions",
        )

        FetchCompetitionStatus.Success -> CompetitionContent(
            state = state,
            onSelectCompetition = onSelectCompetition,
            onSelectSeason = viewModel::selectSeason,
        )
    }

}

@Composable
private fun CompetitionContent(
    state: CompetitionViewModelState,
    onSelectCompetition: (Long) -> Unit,
    onSelectSeason: (Int) -> Unit,
) {
    Column {
        CompetitionHeader(
            allSeasons = state.seasons,
            selectedSeason = state.selectedSeason,
            onSelectSeason = onSelectSeason,
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) {
            items(state.competitions) { competition ->
                Card(
                    colors = CardColors(
                        containerColor = Color.Transparent,
                        contentColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        disabledContentColor = Color.Transparent,
                    ),
                    onClick = { onSelectCompetition(competition.id) },
                    content = { CompetitionCrest(emblem = competition.emblem) },
                )
            }
        }
    }
}

@Composable
private fun CompetitionHeader(
    allSeasons: List<Int>,
    selectedSeason: Int,
    onSelectSeason: (Int) -> Unit,
) {
    var dropDownExpanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Competitions",
            style = MaterialTheme.typography.headlineLarge,
        )

        Row {
            TextButton(
                onClick = { dropDownExpanded = true },
                content = {
                    Text(
                        text = "$selectedSeason",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            )

            SeasonDropDownMenu(
                allSeasons = allSeasons,
                onSelectSeason = onSelectSeason,
                dropDownExpanded = dropDownExpanded,
                onDismiss = { dropDownExpanded = false },
            )
        }
    }
}

@Composable
private fun SeasonDropDownMenu(
    allSeasons: List<Int>,
    onSelectSeason: (Int) -> Unit,
    dropDownExpanded: Boolean,
    onDismiss: () -> Unit,
) {
    DropdownMenu(
        modifier = Modifier.background(color = Color.White),
        expanded = dropDownExpanded,
        onDismissRequest = onDismiss,
    ) {
        allSeasons.forEach { season ->
            DropdownMenuItem(
                text = { Text(text = "$season", style = MaterialTheme.typography.bodyLarge) },
                onClick = {
                    onSelectSeason(season)
                    onDismiss()
                },
            )
        }
    }
}

@Composable
private fun CompetitionCrest(emblem: String) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(all = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
) {
    AsyncImage(
        modifier = Modifier.size(size = 96.dp),
        model = ImageRequest.Builder(LocalContext.current)
            .data(emblem)
            .decoderFactory(SvgDecoder.Factory())
            .crossfade(enable = true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}
