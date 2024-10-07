package nl.jaysh.football.core.domain.usecase

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.competition.CompetitionType
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.repository.FootballRepository
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(private val repository: FootballRepository) {

    operator fun invoke(): Flow<Either<DataError, List<Competition>>> = repository
        .getCompetitions()
        .map(::mapResult)

    private fun mapResult(result: Either<DataError, List<Competition>>) = result
        .map(::filterLeague)

    private fun filterLeague(competitions: List<Competition>): List<Competition> = competitions
        .filter { competition ->
            competition.type == CompetitionType.LEAGUE
        }
}