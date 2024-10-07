package nl.jaysh.football.core.domain.repository

import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import nl.jaysh.football.core.domain.model.competition.Competition
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Match
import nl.jaysh.football.core.domain.model.standing.CompetitionStanding

interface FootballRepository {
    fun getCompetitions(): Flow<Either<DataError, List<Competition>>>
    fun getStandings(competitionId: Long): Flow<Either<DataError, CompetitionStanding>>
    fun getMatches(teamId: Long): Flow<Either<DataError, List<Match>>>
    fun getSeason(): Flow<Int>
    suspend fun setSeason(year: Int)
}