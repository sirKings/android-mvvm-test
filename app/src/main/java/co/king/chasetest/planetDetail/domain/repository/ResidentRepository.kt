package co.king.chasetest.planetDetail.domain.repository

import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.Flow

interface ResidentRepository {
    fun getResident(id: Int): Flow<Resource<Resident>>

}