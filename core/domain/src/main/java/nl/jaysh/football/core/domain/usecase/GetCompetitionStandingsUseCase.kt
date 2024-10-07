package nl.jaysh.football.core.domain.usecase

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding
import nl.jaysh.football.core.domain.repository.FootballRepository
import javax.inject.Inject

class GetCompetitionStandingsUseCase @Inject constructor(
    private val repository: FootballRepository,
) {
    operator fun invoke(competitionId: Long): Flow<Either<DataError, CompetitionStanding>> {
        return repository.getStandings(competitionId = competitionId)
    }
}