package nl.jaysh.football.core.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import nl.jaysh.common.util.Constants.PREFERENCES
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesCacheImpl @Inject constructor(
    @ApplicationContext private val context: Context,
): UserPreferencesCache {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(PREFERENCES)
    private val selectedSeasonKey = intPreferencesKey(SELECTED_SEASON_KEY)

    override fun getSelectedSeason(): Flow<Int> = context.dataStore.data.map { preferences ->
        preferences[selectedSeasonKey] ?: LocalDate.now().year
    }

    override suspend fun setSelectedSeason(year: Int) {
        context.dataStore.edit { preferences ->
            preferences[selectedSeasonKey] = year
        }
    }

    companion object {
        private const val SELECTED_SEASON_KEY: String = "selected_season"
    }
}