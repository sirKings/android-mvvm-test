package co.king.chasetest.planetDetail.data.remote

import co.king.chasetest.planetDetail.data.remote.dto.FilmDto
import co.king.chasetest.planetDetail.data.remote.dto.ResidentDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlanetDetailApi {

    @GET("people/{id}/")
    suspend fun getPeople(@Path("id") id: Int): Response<ResidentDto>

    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: Int): Response<FilmDto>
}