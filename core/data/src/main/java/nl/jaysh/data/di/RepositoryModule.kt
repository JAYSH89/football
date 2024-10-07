package nl.jaysh.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import nl.jaysh.data.repository.FootballRepositoryImpl
import nl.jaysh.football.core.domain.repository.FootballRepository

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun bindsFootballService(footballRepository: FootballRepositoryImpl): FootballRepository
}