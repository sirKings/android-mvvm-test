package co.king.chasetest.planetList.domain.repository

import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlanetListRepository {
    fun fetchPlanetList(page: Int?): Flow<Resource<Pair<Int?, List<Planet>>>>

    fun fetchPlanet(id: Int): Flow<Resource<Planet>>
}