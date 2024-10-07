package nl.jaysh.football.core.domain.usecase

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.repository.FootballRepository
import javax.inject.Inject

class GetMatchesUseCase @Inject constructor(private val repository: FootballRepository) {
    operator fun invoke(teamId: Long): Flow<Either<DataError, List<Match>>> {
        return repository.getMatches(teamId = teamId)
    }
}