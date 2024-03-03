package co.king.chasetest.planetDetail.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import co.king.chasetest.planetDetail.data.local.entity.FilmEntity

@Dao
interface FilmDao {
    @Upsert
    fun saveFilm(film: FilmEntity)

    @Query("select * from film where planetId =:planetId")
    fun getFilmList(planetId: Int): List<FilmEntity>

}