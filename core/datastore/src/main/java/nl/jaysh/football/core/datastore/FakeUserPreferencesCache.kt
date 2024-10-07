package nl.jaysh.football.core.datastore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeUserPreferencesCache @Inject constructor() : UserPreferencesCache {

    private val selectedSeason = MutableStateFlow(2023)

    override fun getSelectedSeason(): Flow<Int> = selectedSeason

    override suspend fun setSelectedSeason(year: Int) {
        selectedSeason.value = year
    }
}