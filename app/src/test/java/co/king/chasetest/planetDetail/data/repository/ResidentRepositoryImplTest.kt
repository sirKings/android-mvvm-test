package co.king.chasetest.planetDetail.data.repository

import co.king.chasetest.planetDetail.data.local.dao.ResidentDao
import co.king.chasetest.planetDetail.data.mapper.toResident
import co.king.chasetest.planetDetail.data.remote.PlanetDetailApi
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.Response

class ResidentRepositoryImplTest {

    lateinit var sut: ResidentRepositoryImpl
    lateinit var mockPlanetDao: PlanetDao
    lateinit var mockApi: PlanetDetailApi
    lateinit var mockResidentDao: ResidentDao


    @Before
    fun setUp() {
        mockApi = mock(PlanetDetailApi::class.java)
        mockResidentDao = mock(ResidentDao::class.java)
        mockPlanetDao = mock(PlanetDao::class.java)
        sut = ResidentRepositoryImpl(mockPlanetDao,mockApi,mockResidentDao)
    }

    @Test
    fun `Given successful network call, getResident should return a Resident`() = runBlocking {
        val testStub = Gson().toJson(listOf("https://swapi.dev/api/people/2/"))
        val r = ResidentDto("Test", "", "", "", "", "", "","", "https://swapi.dev/api/people/2/")
        val p = PlanetEntity(
            2,
            "Test", "", "", "", "", "", "", "", "",
            testStub,testStub, "", "https://swapi.dev/api/planets/2/"
        )

        `when`(mockApi.getPeople(2)).thenReturn(
            Response.success(r)
        )
        `when`(mockPlanetDao.getPlanet(2)).thenReturn(
            p
        )

        val stream = sut.getResident(2).toList()

        verify(mockApi).getPeople(2)
        verify(mockPlanetDao).getPlanet(2)
        assertEquals(stream[0], Resource.Success(data = r.toResident()))
    }

}