package co.king.chasetest.planetList.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import co.king.chasetest.planetList.data.local.entity.PlanetEntity

@Dao
interface PlanetDao {

    @Upsert
    fun savePlanetList(items: List<PlanetEntity>)

    @Query("select * from planet order by id")
    fun observePlanetList(): List<PlanetEntity>

    @Query("select * from planet where id=:id")
    fun getPlanet(id: Int): PlanetEntity?
}