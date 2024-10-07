package nl.jaysh.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test

//@OptIn(ExperimentalCoroutinesApi::class)
class FootballRepositoryImplTest {


    @Test
    fun testAddingTwoNumbers() {
        assertThat(2 + 2).isEqualTo(4)
    }
//    private lateinit var fakeUserPreferencesCache: FakeUserPreferencesCache
//    private lateinit var fakeFootballService: FootballService
//    private lateinit var repository: FootballRepository
//
//    @BeforeTest
//    fun setUp() {
//        fakeUserPreferencesCache = FakeUserPreferencesCache()
//        fakeFootballService = OfflineFootballService()
//        repository = FootballRepository(
//            footballService = fakeFootballService,
//            userPreferences = fakeUserPreferencesCache,
//            dispatcher = UnconfinedTestDispatcher(),
//        )
//    }
//
//    @Test
//    fun `get competition should return list of competition`() = runTest {
//        val result = repository.getCompetitions().first().getOrElse {
//            throw Exception("Error: No competition data")
//        }
//
//        val expected = listOf(
//            CompetitionTestData.eredivisie,
//            CompetitionTestData.worldCup,
//            CompetitionTestData.primeraDivision,
//        )
//
//        assertEquals(expected, result)
//    }
//
//    @Test
//    fun `get standing should return competition standing`() = runTest {
//        val result = repository.getStandings(competitionId = 2163).first().getOrElse {
//            throw Exception("Error: No standing data")
//        }
//
//        val expected = CompetitionStanding(
//            standings = StandingTestData.standing,
//            competition = CompetitionTestData.eredivisie,
//        )
//
//        assertEquals(expected, result)
//    }
//
//    @Test
//    fun `get matches should return list of matches`() = runTest {
//        val teamId = TeamTestData.feyenoord.id
//        val result = repository.getMatches(teamId = teamId).first().getOrElse {
//            throw Exception("Error: No matches data")
//        }
//
//        val expected = MatchesTestData.matches
//
//        assertEquals(expected, result)
////        assertEquals(expected.size, result.size)
//    }
//
//    @Test
//    fun `test get season default`() = runTest {
//        val expectedSeason = 2023
//        val result = repository.getSeason().first()
//        assertEquals(expectedSeason, result)
//    }
//
//    @Test
//    fun `test set season successful`() = runTest {
//        val newSeason = 2024
//        repository.setSeason(newSeason)
//        val result = repository.getSeason().first()
//        assertEquals(newSeason, result)
//    }
}