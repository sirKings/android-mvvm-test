package co.king.chasetest.planetList.domain.repository

import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow

interface PlanetListRepository {
    fun fetchPlanetList(): Flow<Resource<List<Planet>>>
}