package nl.jaysh.network.service

import arrow.core.Either
import arrow.core.Either.Left
import arrow.core.Either.Right
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.ConnectTimeoutException
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import nl.jaysh.football.core.domain.model.error.DataError
import nl.jaysh.network.model.CompetitionResponseDTO
import nl.jaysh.network.model.StandingResponseDTO
import nl.jaysh.network.model.MatchesResponseDTO
import javax.inject.Inject

class FootballServiceImpl @Inject constructor(
    private val httpClient: HttpClient,
) : FootballService {

    override suspend fun getCompetitions(): Either<DataError, CompetitionResponseDTO> {
        val path = "/competitions"
        return fetchApiResponse<CompetitionResponseDTO>(path = path)
    }

    override suspend fun getStanding(
        competitionId: Long,
        season: Int,
    ): Either<DataError, StandingResponseDTO> {
        val path = "/competitions/$competitionId/standings"
        val parameters = mapOf("season" to season.toString())

        return fetchApiResponse<StandingResponseDTO>(path = path, parameters = parameters)
    }

    override suspend fun getMatches(
        teamId: Long,
        season: Int,
    ): Either<DataError, MatchesResponseDTO> {
        val path = "/teams/$teamId/matches"
        val parameters = mapOf("season" to season.toString())

        return fetchApiResponse<MatchesResponseDTO>(path = path, parameters = parameters)
    }

    private suspend inline fun <reified T> fetchApiResponse(
        path: String,
        parameters: Map<String, String>? = null,
    ): Either<DataError, T> = try {
        val response = httpClient.get(urlString = path) {
            parameters?.forEach { (key, value) ->
                parameter(key, value)
            }
        }

        Right(response.body<T>())
    } catch (exception: Exception) {
        Napier.e(message = "Error: ${exception.localizedMessage}")
        when (exception) {
            is HttpRequestTimeoutException,
            is ConnectTimeoutException,
            is SocketTimeoutException,
            -> Left(DataError.Network.REQUEST_TIMEOUT)

            else -> Left(DataError.Network.UNKNOWN)
        }
    }

}