package nl.jaysh.football.core.domain.model.competition

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CompetitionTypeTest {
    @ParameterizedTest
    @CsvSource(
        "LEAGUE, true",
        "CUP, true",
        "UNKNOWN, false",
        "league, false",
        "cup, false",
    )
    fun testCompetitionType(value: String, expected: Boolean) {
        val result = CompetitionType.valueOfOrNull(value)
        val foundCompetitionTypeSuccessful = result != null
        assert(foundCompetitionTypeSuccessful == expected)
    }
}