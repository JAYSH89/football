package nl.jaysh.feature.competition

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nl.jaysh.common.util.Constants
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.usecase.GetCompetitionsUseCase
import nl.jaysh.football.core.domain.usecase.GetSeasonUseCase
import nl.jaysh.football.core.domain.usecase.SetSeasonUseCase
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(
    getCompetitions: GetCompetitionsUseCase,
    getSeason: GetSeasonUseCase,
    private val setSeason: SetSeasonUseCase,
) : ViewModel() {

    val state = combine(
        flow = getCompetitions(),
        flow2 = getSeason(),
        transform = ::toState,
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = CompetitionViewModelState(),
    )

    fun selectSeason(year: Int) {
        val validYear = state.value.seasons.contains(year)
        if (!validYear) return

        viewModelScope.launch {
            setSeason(year = year)
        }
    }

    private fun toState(
        result: Either<DataError, List<Competition>>,
        selectedSeason: Int,
    ): CompetitionViewModelState {
        return result.fold(
            ifLeft = { error -> onError(error, selectedSeason) },
            ifRight = { competition -> onSuccess(competition, selectedSeason) },
        )
    }

    private fun onError(error: DataError, selectedSeason: Int): CompetitionViewModelState {
        return CompetitionViewModelState(
            competitions = emptyList(),
            fetchCompetitionStatus = FetchCompetitionStatus.Error,
            selectedSeason = selectedSeason,
        )
    }

    private fun onSuccess(
        competitions: List<Competition>,
        selectedSeason: Int,
    ): CompetitionViewModelState {
        return CompetitionViewModelState(
            competitions = competitions,
            fetchCompetitionStatus = FetchCompetitionStatus.Success,
            selectedSeason = selectedSeason,
        )
    }
}

data class CompetitionViewModelState(
    val competitions: List<Competition> = emptyList(),
    val fetchCompetitionStatus: FetchCompetitionStatus = FetchCompetitionStatus.Loading,
    val selectedSeason: Int = Constants.getAvailableSeasons().first(),
    val seasons: List<Int> = Constants.getAvailableSeasons(),
)

sealed interface FetchCompetitionStatus {
    data object Loading : FetchCompetitionStatus
    data object Error : FetchCompetitionStatus
    data object Success : FetchCompetitionStatus
}
