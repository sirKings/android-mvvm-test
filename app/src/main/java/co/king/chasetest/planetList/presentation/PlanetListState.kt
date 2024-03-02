package co.king.chasetest.planetList.presentation

import co.king.chasetest.planetList.domain.model.Planet

data class PlanetListState(
    val loading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val error: String? = null
)
