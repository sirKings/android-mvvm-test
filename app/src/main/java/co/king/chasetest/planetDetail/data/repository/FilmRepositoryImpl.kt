package co.king.chasetest.planetDetail.data.repository

import co.king.chasetest.planetDetail.data.local.dao.FilmDao
import co.king.chasetest.planetDetail.data.mapper.toFilm
import co.king.chasetest.planetDetail.data.mapper.toFilmEntity
import co.king.chasetest.planetDetail.data.remote.PlanetDetailApi
import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.repository.FilmRepository
import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.util.Resource
import co.king.chasetest.util.getIdFromUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    val planetDao: PlanetDao,
    val api: PlanetDetailApi,
    val filmDao: FilmDao
) : FilmRepository {
    override fun getFilm(id: Int): Flow<Resource<Film>> {
        return flow {
            val planet = planetDao.getPlanet(id)?.toPlanet()
            planet?.films?.map {
                val film = try {
                    api.getFilm(getIdFromUrl(it)).body()
                } catch (e: Exception) {
                    e.printStackTrace()
                    val localData = filmDao.getFilmList(id)
                    localData.map { f ->
                        emit(Resource.Success(data = f.toFilm()))
                    }
                    return@flow
                }

                film?.let { f ->
                    filmDao.saveFilm(f.toFilmEntity(id))
                    emit(Resource.Success(data = f.toFilm()))
                }
            }
        }
    }

}