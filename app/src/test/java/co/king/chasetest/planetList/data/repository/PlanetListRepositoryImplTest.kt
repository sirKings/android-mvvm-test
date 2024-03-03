package co.king.chasetest.planetList.data.repository

import co.king.chasetest.planetList.data.local.PlanetDao
import co.king.chasetest.planetList.data.local.entity.PlanetEntity
import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.planetList.data.remote.PlanetListApi
import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.data.remote.dto.PlanetListResponse
import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response

class PlanetListRepositoryImplTest {

    lateinit var sut: PlanetListRepositoryImpl
    lateinit var mockPlanetDao: PlanetDao
    lateinit var mockApi: PlanetListApi

    @Before
    fun setUp() {
        mockApi = mock(PlanetListApi::class.java)
        mockPlanetDao = mock(PlanetDao::class.java)
        sut = PlanetListRepositoryImpl(mockPlanetDao, mockApi)
    }

    @Test
    fun `Given successful network call, fetch planet list should return planets`() = runBlocking {
        val p = PlanetDto(
            "Test", "", "", "", "", "", "", "", "",
            emptyList(),
            emptyList(), "", "https://swapi.dev/api/planets/2/"
        )
        `when`(mockApi.fetchPlanetList(null)).thenReturn(
            Response.success(
                PlanetListResponse(
                    1, null, null, listOf(
                        p
                    )
                )
            )
        )

        val stream = sut.fetchPlanetList(null).toList()

        verify(mockApi).fetchPlanetList(null)
        assertEquals(
            stream[1], Resource.Success(
                Pair(
                    null, listOf(
                       p.toPlanet()
                    )
                )
            )
        )
    }


    @Test
    fun `Given failed network call, fetch planet should return an error`() = runBlocking {
        val e = HttpException(
            Response.error<Any>(
                404, ResponseBody.create(null, "")
            )
        )
        `when`(mockApi.fetchPlanetList(null)).thenThrow(
            e
        )

        val stream = sut.fetchPlanetList(null).toList()

        verify(mockApi).fetchPlanetList(null)
        assertEquals(
            stream[1],
            Resource.Failure(data = null, error = e.localizedMessage ?: "An error occurred")
        )
    }

    @Test
    fun `Given a saved planet in the database, fetch planet should return the saved planet`() = runBlocking {
        val testStub = Gson().toJson(listOf("Hello"))
        val p = PlanetEntity(
            2,
            "Test", "", "", "", "", "", "", "", "",
            testStub,testStub, "", "https://swapi.dev/api/planets/2/"
        )
        `when`(mockPlanetDao.getPlanet(2)).thenReturn(
            p
        )
        val stream = sut.fetchPlanet(2).toList()
        verify(mockPlanetDao).getPlanet(2)
        assertEquals(stream[0], Resource.Success(data = p.toPlanet()))
    }
}