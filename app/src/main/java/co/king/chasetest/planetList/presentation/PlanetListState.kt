package co.king.chasetest.planetList.presentation

import co.king.chasetest.planetList.domain.model.Planet

data class PlanetListState(
    val loading: Boolean = false,
    val planets: ArrayList<Planet> = arrayListOf(),
    val error: String? = null,
    val nextPage: Int? = 0
)
