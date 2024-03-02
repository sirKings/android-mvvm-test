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
        url = url
    )