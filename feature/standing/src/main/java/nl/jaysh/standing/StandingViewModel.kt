package nl.jaysh.standing

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nl.jaysh.common.util.Constants.COMPETITION_ID_KEY
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding
import nl.jaysh.football.core.domain.usecase.GetCompetitionStandingsUseCase
import javax.inject.Inject

@HiltViewModel
class StandingViewModel @Inject constructor(
    getCompetitionStandings: GetCompetitionStandingsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val competitionId = savedStateHandle
        .getStateFlow<Long?>(key = COMPETITION_ID_KEY, initialValue = null)
        .filterNotNull()

    @OptIn(ExperimentalCoroutinesApi::class)
    val state: StateFlow<StandingViewModelState> = competitionId
        .flatMapLatest { id -> getCompetitionStandings(competitionId = id) }
        .map(::toState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = StandingViewModelState(),
        )

    private fun toState(result: Either<DataError, CompetitionStanding>): StandingViewModelState {
        return result.fold(
            ifLeft = ::onError,
            ifRight = ::onSuccess,
        )
    }

    private fun onSuccess(standing: CompetitionStanding) = StandingViewModelState(
        standings = standing,
        fetchStandingStatus = FetchStandingStatus.Success,
    )

    private fun onError(error: DataError) = StandingViewModelState(
        standings = null,
        fetchStandingStatus = FetchStandingStatus.Error,
    )
}

data class StandingViewModelState(
    val standings: CompetitionStanding? = null,
    val fetchStandingStatus: FetchStandingStatus = FetchStandingStatus.Loading,
)

sealed interface FetchStandingStatus {
    data object Loading : FetchStandingStatus
    data object Error : FetchStandingStatus
    data object Success : FetchStandingStatus
}
