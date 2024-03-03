package co.king.chasetest.planetList.presentation

import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import co.king.chasetest.util.Resource
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class PlanetListViewModelTest{
    lateinit var sut: PlanetListViewModel
    var mockRepository: PlanetListRepository? = null

    @Before
    fun setup(){
        mockRepository = mock(PlanetListRepository::class.java)
    }

    @After
    fun tearDown(){
        mockRepository = null
    }

    @Test
    fun `Given successful network call, planet list state should contain a planet`() = runBlocking {
        val p = PlanetDto(
            "Test", "", "", "", "", "", "", "", "",
            emptyList(),
            emptyList(), "", "https://swapi.dev/api/planets/2/"
        )
        `when`(mockRepository?.fetchPlanetList(null)).thenReturn(
            flowOf(Resource.Success(data = Pair(null, listOf(p.toPlanet()))))
        )
        sut = PlanetListViewModel(mockRepository!!)
        verify(mockRepository)?.fetchPlanetList(null)
        assertTrue(sut.state.value.planets.contains(p.toPlanet()))
    }

    @Test
    fun `Given failed network call, planet list error state should contain error`() = runBlocking {
        val errorMessage = "Error occurred"
        `when`(mockRepository?.fetchPlanetList(null)).thenReturn(flowOf(Resource.Failure(data = null,error = errorMessage)))

        sut = PlanetListViewModel(mockRepository!!)

        verify(mockRepository)?.fetchPlanetList(null)
        assertEquals(errorMessage, sut.state.value.error)
    }
}