package co.king.chasetest.planetDetail.data.repository

import co.king.chasetest.planetDetail.data.local.dao.FilmDao
import co.king.chasetest.planetDetail.data.mapper.toFilm
import co.king.chasetest.planetDetail.data.mapper.toResident
import co.king.chasetest.planetDetail.data.remote.PlanetDetailApi
import co.king.chasetest.planetDetail.data.remote.dto.FilmDto
import co.king.chasetest.planetDetail.data.remote.dto.ResidentDto
import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.local.entity.PlanetEntity
import co.king.chasetest.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import retrofit2.Response

class FilmRepositoryImplTest {
    lateinit var sut: FilmRepositoryImpl
    lateinit var mockApi: PlanetDetailApi
    lateinit var mockPlanetDao: PlanetDao
    lateinit var mockFilmDao: FilmDao

    @Before
    fun setUp() {
        mockApi = mock(PlanetDetailApi::class.java)
        mockPlanetDao = mock(PlanetDao::class.java)
        mockFilmDao = mock(FilmDao::class.java)
        sut = FilmRepositoryImpl(mockPlanetDao,mockApi,mockFilmDao)
    }

    @Test
    fun `Given successful network call, getFilm should return a Film`() = runBlocking {
        val testStub = Gson().toJson(listOf("https://swapi.dev/api/film/2/"))
        val r = FilmDto("Test", 2, "", "", "", "", "https://swapi.dev/api/film/2/")
        val p = PlanetEntity(
            2,
            "Test", "", "", "", "", "", "", "", "",
            testStub,testStub, "", "https://swapi.dev/api/planets/2/"
        )

        Mockito.`when`(mockApi.getFilm(2)).thenReturn(
            Response.success(r)
        )
        Mockito.`when`(mockPlanetDao.getPlanet(2)).thenReturn(
            p
        )

        val stream = sut.getFilm(2).toList()

        Mockito.verify(mockApi).getFilm(2)
        Mockito.verify(mockPlanetDao).getPlanet(2)
        assertEquals(stream[0], Resource.Success(data = r.toFilm()))
    }
}