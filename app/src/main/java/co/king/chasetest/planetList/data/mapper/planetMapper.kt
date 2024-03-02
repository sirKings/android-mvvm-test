package co.king.chasetest.planetList.data.mapper

import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.domain.model.Planet

fun PlanetDto.toPlanet(): Planet =
    Planet(
        name = name,
        rotationPeriod = rotation_period,
        orbitalPeriod = orbital_period,
        diameter = diameter,
        climate = climate,
        gravity = gravity,
        terrain = terrain,
        surfaceWater = surface_water,
        population = population,
        residentsCount = residents.size,
        filmsCount = films.size,
        createdAt = created,
        url = url,
        id = getIdFromUrl(url)
    )

private fun getIdFromUrl(url: String): Int{
    //"https://swapi.dev/api/planets/2/"
    return try {
        val start = url.length - 2
        val end = url.length -2
        val char = url.slice(start..end)
        char.toInt()
    }catch (e: Exception){
        0
    }
}