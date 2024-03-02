package co.king.chasetest.planetList.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class PlanetListViewModel @Inject constructor(
    private val repository: PlanetListRepository
): ViewModel() {
    private val _state = MutableStateFlow(PlanetListState())
    val state = _state.asStateFlow()

    init {
        fetchPlanets(null)
    }

    fun loadMorePlanets(){
        if(!_state.value.loading ){
            _state.value.nextPage?.let {
                fetchPlanets(it)
            }
        }
    }

    private fun fetchPlanets(page: Int?) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchPlanetList(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        _state.update {planetListState ->
                            planetListState.copy(loading = true)
                        }
                    }

                    is Resource.Failure -> {
                        _state.update { planetListState ->
                            planetListState.copy(error = it.error, loading = false)
                        }
                    }

                    is Resource.Success -> {
                        _state.update { planetListState ->
                            planetListState.planets.addAll(it.data.second)
                            planetListState.copy(
                                nextPage = it.data.first, loading = false)
                        }
                    }
                }
            }
        }
    }
}