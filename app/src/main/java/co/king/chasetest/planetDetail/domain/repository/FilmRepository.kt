package co.king.chasetest.planetDetail.domain.repository

import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    fun getFilm(id: Int): Flow<Resource<Film>>

}