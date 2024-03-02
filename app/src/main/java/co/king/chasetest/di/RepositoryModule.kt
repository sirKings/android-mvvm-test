package co.king.chasetest.di

import co.king.chasetest.planetList.data.repository.PlanetListRepositoryImpl
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindPlanetListRepository(
        repositoryImpl: PlanetListRepositoryImpl
    ): PlanetListRepository
}