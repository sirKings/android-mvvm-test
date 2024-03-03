package co.king.chasetest.planetDetail.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.king.chasetest.planetDetail.data.local.dao.FilmDao
import co.king.chasetest.planetDetail.data.local.dao.ResidentDao
import co.king.chasetest.planetDetail.data.local.entity.FilmEntity
import co.king.chasetest.planetDetail.data.local.entity.ResidentEntity


@Database(
    entities = [FilmEntity::class, ResidentEntity::class],
    version = 1
)
abstract class PlanetDetailDatabase: RoomDatabase() {
    abstract val filmDao: FilmDao
    abstract val residentDao: ResidentDao
}