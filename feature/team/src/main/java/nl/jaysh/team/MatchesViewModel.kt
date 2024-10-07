package nl.jaysh.team

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
import nl.jaysh.common.util.Constants.TEAM_ID_KEY
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.usecase.GetMatchesUseCase
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MatchesViewModel @Inject constructor(
    getMatches: GetMatchesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val teamId = savedStateHandle
        .getStateFlow<Long?>(key = TEAM_ID_KEY, initialValue = null)
        .filterNotNull()

    val state: StateFlow<MatchesViewModelState> = teamId
        .flatMapLatest { teamId -> getMatches(teamId = teamId) }
        .map(::toState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = MatchesViewModelState.Loading,
        )

    private fun toState(result: Either<DataError, List<Match>>): MatchesViewModelState {
        return result.fold(
            ifLeft = { MatchesViewModelState.Error },
            ifRight = { matches -> MatchesViewModelState.Success(matches = matches) },
        )
    }
}

sealed interface MatchesViewModelState {
    data object Loading : MatchesViewModelState
    data object Error : MatchesViewModelState
    data class Success(val matches: List<Match>) : MatchesViewModelState
}
