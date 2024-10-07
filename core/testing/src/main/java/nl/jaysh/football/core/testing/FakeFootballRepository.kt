package nl.jaysh.football.core.testing

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding
import nl.jaysh.football.core.domain.repository.FootballRepository
import nl.jaysh.football.core.testing.data.StandingTestData

class FakeFootballRepository : FootballRepository {

    private val competitions: MutableStateFlow<Either<DataError, List<Competition>>>
        get() {
            val initialValue = Either.Right<List<Competition>>(emptyList())
            return MutableStateFlow(initialValue)
        }

    private val competitionStandings: MutableStateFlow<Either<DataError, CompetitionStanding>>
        get() {
            val initialValue = Either.Right(StandingTestData.competitionStanding)
            return MutableStateFlow(initialValue)
        }

    private val matches: MutableStateFlow<Either<DataError, List<Match>>>
        get() {
            val initialValue = Either.Right<List<Match>>(emptyList())
            return MutableStateFlow(initialValue)
        }

    private val season: MutableStateFlow<Int> = MutableStateFlow(2024)

    override fun getCompetitions(): Flow<Either<DataError, List<Competition>>> {
        return competitions.asStateFlow()
    }

    override fun getStandings(competitionId: Long): Flow<Either<DataError, CompetitionStanding>> {
        return competitionStandings.asStateFlow()
    }

    override fun getMatches(teamId: Long): Flow<Either<DataError, List<Match>>> {
        return matches.asStateFlow()
    }

    override fun getSeason(): Flow<Int> {
        return season.asStateFlow()
    }

    override suspend fun setSeason(year: Int) {
        season.update { year }
    }

    // Helper functions
    suspend fun setCompetitions(data: Either<DataError, List<Competition>>) {
        competitions.emit(data)
    }

    suspend fun setStandings(data: Either<DataError, CompetitionStanding>) {
        competitionStandings.emit(data)
    }

    suspend fun setMatches(data: Either<DataError, List<Match>>) {
        matches.emit(data)
    }
}