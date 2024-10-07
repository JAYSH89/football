package nl.jaysh.football.core.domain.usecase

import kotlinx.coroutines.flow.Flow
import nl.jaysh.football.core.domain.repository.FootballRepository
import javax.inject.Inject

class GetSeasonUseCase @Inject constructor(private val repository: FootballRepository) {
    operator fun invoke(): Flow<Int> = repository.getSeason()
}