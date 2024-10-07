package nl.jaysh.football.core.domain.usecase

import io.mockk.mockk
import nl.jaysh.football.core.domain.repository.FootballRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCompetitionStandingsUseCaseTest {

    private lateinit var repository: FootballRepository

    @BeforeEach
    fun setUp() {
        repository = mockk()
    }

    @Test
    fun `no data returns empty list`() {

    }

    @Test
    fun `list of competitions only contains LEAGUE CompetitionType`() {

    }

    @Test
    fun `list of competitions is ordered by name`() {

    }

    @Test
    fun `returns DataError if error`() {

    }
}