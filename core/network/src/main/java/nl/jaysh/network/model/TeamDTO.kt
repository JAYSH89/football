package nl.jaysh.network.model

import kotlinx.serialization.Serializable
import nl.jaysh.football.core.domain.model.Team

@Serializable
data class TeamDTO(
    val id: Long,
    val name: String,
    val shortName: String,
    val tla: String,
    val crest: String,
)

fun TeamDTO.toTeam(): Team = Team(
    id = id,
    name = name,
    shortName = shortName,
    crest = crest,
)
