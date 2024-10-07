package nl.jaysh.network.service

import arrow.core.Either
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.network.model.CompetitionResponseDTO
import nl.jaysh.network.model.MatchesResponseDTO
import nl.jaysh.network.model.StandingResponseDTO

interface FootballService {
    suspend fun getCompetitions(): Either<DataError, CompetitionResponseDTO>

    suspend fun getStanding(
        competitionId: Long,
        season: Int,
    ): Either<DataError, StandingResponseDTO>

    suspend fun getMatches(
        teamId: Long,
        season: Int,
    ): Either<DataError, MatchesResponseDTO>
}