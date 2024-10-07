package nl.jaysh.data.repository

import arrow.core.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nl.jaysh.common.di.IoDispatcher
import nl.jaysh.football.core.datastore.UserPreferencesCache
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding
import nl.jaysh.football.core.domain.repository.FootballRepository
import nl.jaysh.network.model.toMatch
import nl.jaysh.network.model.toCompetition
import nl.jaysh.network.model.toCompetitionStanding
import nl.jaysh.network.service.FootballService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FootballRepositoryImpl @Inject constructor(
    private val footballService: FootballService,
    private val userPreferences: UserPreferencesCache,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
): FootballRepository {
    override fun getCompetitions(): Flow<Either<DataError, List<Competition>>> = flow {
        val competitions = footballService
            .getCompetitions()
            .map {
                it.competitions.map { dto -> dto.toCompetition() }
            }

        emit(competitions)
    }.flowOn(context = dispatcher)

    override fun getStandings(competitionId: Long): Flow<Either<DataError, CompetitionStanding>> {
        return flow {
            footballService
                .getStanding(competitionId = competitionId, season = getSeason().first())
                .map { competition -> competition.toCompetitionStanding() }
                .also { standing -> emit(standing) }
        }.flowOn(context = dispatcher)
    }

    override fun getMatches(teamId: Long): Flow<Either<DataError, List<Match>>> = flow {
        val matches = footballService
            .getMatches(teamId = teamId, season = getSeason().first())
            .map { response ->
                response.matches.map { dto -> dto.toMatch() }
            }

        emit(matches)
    }.flowOn(context = dispatcher)

    override fun getSeason(): Flow<Int> {
        return userPreferences.getSelectedSeason()
    }

    override suspend fun setSeason(year: Int) {
        userPreferences.setSelectedSeason(year = year)
    }
}