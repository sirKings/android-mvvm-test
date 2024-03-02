package co.king.chasetest.planetList.data.remote

import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.data.remote.dto.PlanetListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PlanetListApi {

    @GET("planets")
    suspend fun fetchPlanetList(@Query("page") page: Int?): Response<PlanetListResponse>
}