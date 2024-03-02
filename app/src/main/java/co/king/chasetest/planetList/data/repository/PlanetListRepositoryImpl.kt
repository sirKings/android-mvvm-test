package co.king.chasetest.planetList.data.repository

import co.king.chasetest.planetList.data.remote.PlanetListApi
import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetListRepositoryImpl @Inject constructor(
   val api: PlanetListApi
) : PlanetListRepository {
    override fun fetchPlanetList(): Flow<Resource<List<Planet>>> {
        return flow {

        }
    }
}