package co.king.chasetest.di

import co.king.chasetest.planetDetail.data.repository.FilmRepositoryImpl
import co.king.chasetest.planetDetail.data.repository.ResidentRepositoryImpl
import co.king.chasetest.planetDetail.domain.repository.FilmRepository
import co.king.chasetest.planetDetail.domain.repository.ResidentRepository
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

    @Binds
    @Singleton
    abstract fun bindFilmRepository(
        repositoryImpl: FilmRepositoryImpl
    ): FilmRepository

    @Binds
    @Singleton
    abstract fun bindResidentRepository(
        repositoryImpl: ResidentRepositoryImpl
    ): ResidentRepository
}