package co.king.chasetest.planetDetail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.king.chasetest.main.MainActivity.Companion.PLANET_ID
import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.planetDetail.domain.repository.FilmRepository
import co.king.chasetest.planetDetail.domain.repository.ResidentRepository
import co.king.chasetest.planetList.domain.repository.PlanetListRepository
import co.king.chasetest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val filmRepository: FilmRepository,
    private val residentRepository: ResidentRepository,
    private val planetListRepository: PlanetListRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlanetDetailState())
    val state = _state.asStateFlow()

    init {
        val id = savedStateHandle.get<Int>(PLANET_ID)
        id?.let {
            fetchPlanet(it)
            fetchFilms(it)
            fetchResidents(it)
        }
    }

    private fun fetchResidents(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            residentRepository.getResident(id).collect {
                when (it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Failure -> {
                    }

                    is Resource.Success -> {
                        _state.update { planetDetailsState ->
                            val items = arrayListOf<Resident>()
                            items.addAll(
                                planetDetailsState.residents)
                            items.add(it.data)
                            planetDetailsState.copy(residents = items)
                        }
                    }
                }
            }
        }
    }

    private fun fetchFilms(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            filmRepository.getFilm(id).collect {
                when (it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Failure -> {
                    }

                    is Resource.Success -> {
                        _state.update { planetDetailsState ->
                            val items = arrayListOf<Film>()
                            items.addAll(
                                planetDetailsState.films)
                            items.add(it.data)
                            planetDetailsState.copy(films = items)
                        }
                    }
                }
            }
        }
    }

    private fun fetchPlanet(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            planetListRepository.fetchPlanet(id).collect {
                when (it) {
                    is Resource.Loading -> {
                    }

                    is Resource.Failure -> {
                    }

                    is Resource.Success -> {
                        _state.update { planetDetailsState ->
                            planetDetailsState.copy(planet = it.data, planetId = it.data.id)
                        }
                    }
                }
            }
        }
    }
}
