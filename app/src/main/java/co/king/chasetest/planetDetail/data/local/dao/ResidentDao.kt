package co.king.chasetest.planetDetail.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import co.king.chasetest.planetDetail.data.local.entity.ResidentEntity

@Dao
interface ResidentDao {
    @Upsert
    fun saveResident(resident: ResidentEntity)

    @Query("select * from resident where planetId =:planetId")
    fun getResidentList(planetId: Int): List<ResidentEntity>
}