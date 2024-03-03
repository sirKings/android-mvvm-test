package co.king.chasetest.planetDetail.presentation

import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.planetList.domain.model.Planet

data class PlanetDetailState(
    val planetId: Int = 0,
    val planet: Planet? = null,
    val residents: ArrayList<Resident> = arrayListOf(),
    val films: ArrayList<Film> = arrayListOf(),
)
