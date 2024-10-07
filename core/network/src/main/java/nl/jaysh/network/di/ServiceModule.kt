package nl.jaysh.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import nl.jaysh.network.service.FootballService
import nl.jaysh.network.service.FootballServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Provides
    @Singleton
    fun providesFootballService(httpClient: HttpClient): FootballService {
        return FootballServiceImpl(httpClient = httpClient)
    }
}