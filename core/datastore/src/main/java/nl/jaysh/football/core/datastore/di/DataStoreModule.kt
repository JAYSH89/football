package nl.jaysh.football.core.datastore.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import nl.jaysh.football.core.datastore.UserPreferencesCache
import nl.jaysh.football.core.datastore.UserPreferencesCacheImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Singleton
    @Provides
    fun providesUserPreferencesCache(@ApplicationContext context: Context): UserPreferencesCache {
        return UserPreferencesCacheImpl(context = context)
    }
}
