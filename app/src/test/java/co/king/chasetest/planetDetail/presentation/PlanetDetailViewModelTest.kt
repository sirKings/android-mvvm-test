package co.king.chasetest.planetDetail.presentation

import androidx.lifecycle.SavedStateHandle
import co.king.chasetest.main.MainActivity.Companion.PLANET_ID
import co.king.chasetest.planetDetail.data.mapper.toFilm
import co.king.chasetest.planetDetail.data.mapper.toResident
import co.king.chasetest.planetDetail.data.remote.dto.FilmDto
import co.king.chasetest.planetDetail.data.remote.dto.ResidentDto
import co.king.chasetest.planetDetail.domain.repository.FilmRepository
import co.king.chasetest.planetDetail.domain.repository.ResidentRepository
import co.king.chasetest.planetList.data.local.entity.PlanetEntity
import co.king.chasetest.planetList.data.mapper.toPlanet
import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import co.king.chasetest.planetList.presentation.PlanetListViewModel
import co.king.chasetest.util.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class PlanetDetailViewModelTest{

    var mockFilmRepo: FilmRepository? = null
    var mockResidentRepo: ResidentRepository? = null
    lateinit var sut: PlanetDetailViewModel
    var savedStateHandle: SavedStateHandle? = null
    var mockPlanetRepo: PlanetListRepository? = null


    @Before
    fun setup(){
        mockFilmRepo = mock(FilmRepository::class.java)
        mockResidentRepo = mock(ResidentRepository::class.java)
        savedStateHandle = SavedStateHandle(mapOf(PLANET_ID to 2))
        mockPlanetRepo = mock(PlanetListRepository::class.java)
    }

    @After
    fun tearDown(){
        mockPlanetRepo = null
        mockResidentRepo = null
        savedStateHandle = null
        mockFilmRepo = null
    }

    @Test
    fun `Given a saved planet, planet detail state should contain a planet`() = runBlocking {
        val testStub = Gson().toJson(listOf("Hello"))
        val p = PlanetEntity(
            2,
            "Test", "", "", "", "", "", "", "", "",
            testStub,testStub, "", "https://swapi.dev/api/planets/2/"
        )
        Mockito.`when`(mockPlanetRepo?.fetchPlanet(2)).thenReturn(
            flowOf(Resource.Success(data = (p.toPlanet())))
        )
        sut = PlanetDetailViewModel(savedStateHandle!!,mockFilmRepo!!,mockResidentRepo!!,mockPlanetRepo!!)
        Mockito.verify(mockPlanetRepo)?.fetchPlanet(2)
        assertEquals(p.toPlanet(), sut.state.value.planet)
    }

    @Test
    fun `Given successful network call, planet detail state should contain a resident`() = runBlocking {
        val r = ResidentDto("Test", "", "", "", "", "", "","", "https://swapi.dev/api/people/2/")

        Mockito.`when`(mockResidentRepo?.getResident(2))
            .thenReturn(flowOf(Resource.Success(data = r.toResident())))

        sut = PlanetDetailViewModel(savedStateHandle!!,mockFilmRepo!!,mockResidentRepo!!,mockPlanetRepo!!)

        Mockito.verify(mockResidentRepo)?.getResident(2)
        assertTrue(sut.state.value.residents.contains(r.toResident()))
    }

    @Test
    fun `Given successful network call, planet detail state should contain a film`() = runBlocking {
        val r = FilmDto("Test", 2, "", "", "", "", "https://swapi.dev/api/film/2/")

        Mockito.`when`(mockFilmRepo?.getFilm(2))
            .thenReturn(flowOf(Resource.Success(data = r.toFilm())))

        sut = PlanetDetailViewModel(savedStateHandle!!,mockFilmRepo!!,mockResidentRepo!!,mockPlanetRepo!!)

        Mockito.verify(mockFilmRepo)?.getFilm(2)
        assertTrue(sut.state.value.films.contains(r.toFilm()))
    }

}