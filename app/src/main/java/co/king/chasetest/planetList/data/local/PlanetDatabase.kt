package co.king.chasetest.planetList.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.king.chasetest.planetList.data.local.entity.PlanetEntity

@Database(
    entities = [PlanetEntity::class],
    version = 1
)
abstract class PlanetDatabase: RoomDatabase() {
    abstract val planetDao: PlanetDao
}