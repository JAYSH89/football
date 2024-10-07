package nl.jaysh.football.core.datastore

import kotlinx.coroutines.flow.Flow

interface UserPreferencesCache {
    fun getSelectedSeason(): Flow<Int>
    suspend fun setSelectedSeason(year: Int)
}
