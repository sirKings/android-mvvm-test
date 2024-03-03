package co.king.chasetest.planetList.data.repository

import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.planetList.data.mapper.toPlanetEntity
import co.king.chasetest.planetList.data.remote.PlanetListApi
import co.king.chasetest.planetList.data.remote.dto.PlanetListResponse
import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlanetListRepositoryImpl @Inject constructor(
    val planetDao: PlanetDao,
    val api: PlanetListApi
) : PlanetListRepository {
    override fun fetchPlanetList(page: Int?): Flow<Resource<Pair<Int?, List<Planet>>>> {
        return flow {
            emit(Resource.Loading())
            val remoteResponse = try {
                api.fetchPlanetList(page).body()
            } catch (e: Exception) {
                e.printStackTrace()

                val localData = planetDao.observePlanetList()
                if(localData.isNotEmpty()){
                    emit(Resource.Success(data = Pair(null, planetDao.observePlanetList().map { it.toPlanet() })))
                }else{
                    emit(
                        Resource.Failure(
                            data = null,
                            error = e.localizedMessage ?: "An error occurred"
                        )
                    )
                }
                return@flow
            }
            val planets = remoteResponse?.results?.map {
                it.toPlanet()
            }
            planets?.let { list ->
                planetDao.savePlanetList(list.map { it.toPlanetEntity() })
                emit(Resource.Success(data = Pair(getPageQuery(remoteResponse.next), list)))
            }
        }
    }

    private fun getPageQuery(link: String?): Int? {
        val pageChar = link?.last()
        return try {
            pageChar?.digitToInt()
        } catch (e: Exception) {
            null
        }
    }
}