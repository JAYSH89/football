package nl.jaysh.football.core.domain.usecase

import nl.jaysh.football.core.domain.repository.FootballRepository
import javax.inject.Inject

class SetSeasonUseCase @Inject constructor(private val repository: FootballRepository) {
    suspend operator fun invoke(year: Int) {
        repository.setSeason(year = year)
    }
}