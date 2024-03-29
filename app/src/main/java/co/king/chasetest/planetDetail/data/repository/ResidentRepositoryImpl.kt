package co.king.chasetest.planetDetail.data.repository

import co.king.chasetest.planetDetail.data.local.dao.ResidentDao
import co.king.chasetest.planetDetail.data.mapper.toFilm
import co.king.chasetest.planetDetail.data.mapper.toResident
import co.king.chasetest.planetDetail.data.mapper.toResidentEntity
import co.king.chasetest.planetDetail.data.remote.PlanetDetailApi
import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.planetDetail.domain.repository.ResidentRepository
import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.util.Resource
import co.king.chasetest.util.getIdFromUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ResidentRepositoryImpl @Inject constructor(
    val planetDao: PlanetDao,
    val api: PlanetDetailApi,
    val residentDao: ResidentDao
) : ResidentRepository {
    override fun getResident(id: Int): Flow<Resource<Resident>> {
        return flow {
            val planet = planetDao.getPlanet(id)?.toPlanet()
            planet?.residents?.map {
                val resident = try {
                    api.getPeople(getIdFromUrl(it)).body()
                } catch (e: Exception) {
                    e.printStackTrace()
                    val localData = residentDao.getResidentList(id)
                    localData.map { f ->
                        emit(Resource.Success(data = f.toResident()))
                    }
                    return@flow
                }
                resident?.let { f ->
                    residentDao.saveResident(f.toResidentEntity(id))
                    emit(Resource.Success(data = f.toResident()))
                }
            }
        }
    }

}