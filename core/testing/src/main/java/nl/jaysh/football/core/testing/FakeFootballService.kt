package nl.jaysh.football.core.testing

import arrow.core.Either
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.football.core.domain.model.matches.Winner
import nl.jaysh.network.model.CompetitionResponseDTO
import nl.jaysh.network.model.FullTimeScoreDTO
import nl.jaysh.network.model.MatchesDTO
import nl.jaysh.network.model.MatchesResponseDTO
import nl.jaysh.network.model.ScoreDTO
import nl.jaysh.network.model.StandingResponseDTO
import nl.jaysh.network.service.FootballService
import java.time.LocalDateTime

class FakeFootballService : FootballService {

    private var competitions: Either<DataError, CompetitionResponseDTO> = Either.Right(
        CompetitionResponseDTO(
            competitions = listOf(
                FakeFootballData.eredivisie,
                FakeFootballData.worldCup,
                FakeFootballData.primeraDivision,
            ),
        ),
    )

    private var standings: Either<DataError, StandingResponseDTO> = Either.Right(
        StandingResponseDTO(
            standings = FakeFootballData.standings,
            competition = FakeFootballData.eredivisie,
        ),
    )

    private var matches: Either<DataError, MatchesResponseDTO> = Either.Right(
        MatchesResponseDTO(
            matches = listOf(
                MatchesDTO(
                    id = 441568,
                    matchDay = 10,
                    homeTeam = FakeFootballData.fcTwente,
                    awayTeam = FakeFootballData.feyenoord,
                    score = ScoreDTO(
                        winner = Winner.HOME_TEAM,
                        fullTime = FullTimeScoreDTO(home = 2, away = 1),
                    ),
                    date = LocalDateTime.of(2023, 10, 29, 11, 15, 0, 0),
                ),
                MatchesDTO(
                    id = 441604,
                    matchDay = 14,
                    homeTeam = FakeFootballData.psv,
                    awayTeam = FakeFootballData.feyenoord,
                    score = ScoreDTO(
                        winner = Winner.AWAY_TEAM,
                        fullTime = FullTimeScoreDTO(home = 1, away = 2),
                    ),
                    date = LocalDateTime.of(2023, 12, 3, 11, 15, 0, 0),
                ),
            ),
        )
    )

    override suspend fun getCompetitions(): Either<DataError, CompetitionResponseDTO> {
        return competitions
    }

    override suspend fun getStanding(
        competitionId: Long,
        season: Int,
    ): Either<DataError, StandingResponseDTO> {
        return standings
    }

    override suspend fun getMatches(
        teamId: Long,
        season: Int,
    ): Either<DataError, MatchesResponseDTO> {
        return matches
    }

    // Test helper functions
    fun setCompetitions(data: Either<DataError, CompetitionResponseDTO>) {
        competitions = data
    }

    fun setStanding(data: Either<DataError, StandingResponseDTO>) {
        standings = data
    }

    fun setMatches(data: Either<DataError, MatchesResponseDTO>) {
        matches = data
    }
}