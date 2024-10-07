package nl.jaysh.football.core.domain.usecase

import io.mockk.mockk
import nl.jaysh.football.core.domain.repository.FootballRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

class GetMatchesUseCaseTest {

    private lateinit var repository: FootballRepository

    @BeforeEach
    fun setUp() {
        repository = mockk()
    }
}