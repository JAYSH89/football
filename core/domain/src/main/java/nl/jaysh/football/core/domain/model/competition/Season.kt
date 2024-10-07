package nl.jaysh.football.core.domain.model.competition

import java.time.LocalDate

data class Season(
    val id: Long,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val currentMatchDay: Int?,
)
