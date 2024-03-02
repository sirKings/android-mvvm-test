package co.king.chasetest.planetList.data.remote

import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.data.remote.dto.PlanetListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlanetListApi {

    @GET
    suspend fun fetchPlanetList(): Response<PlanetListResponse>
}