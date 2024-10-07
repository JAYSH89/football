package nl.jaysh.network.model

import kotlinx.serialization.Serializable

@Serializable
data class FullTimeScoreDTO(
    val home: Int? = null,
    val away: Int? = null,
)
