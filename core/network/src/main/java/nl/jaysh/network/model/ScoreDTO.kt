package nl.jaysh.network.model

import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.matches.Winner
import nl.jaysh.network.serializers.WinnerSerializer

@Serializable
data class ScoreDTO(
    @Serializable(with = WinnerSerializer::class) val winner: Winner? = null,
    val fullTime: FullTimeScoreDTO,
)