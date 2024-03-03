package co.king.chasetest.planetList.data.mapper

import co.king.chasetest.planetList.data.local.entity.PlanetEntity
import co.king.chasetest.planetList.data.remote.dto.PlanetDto
import co.king.chasetest.planetList.domain.model.Planet
import co.king.chasetest.util.getIdFromUrl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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
        residents = residents,
        films = films,
        createdAt = created,
        url = url,
        id = getIdFromUrl(url)
    )

fun Planet.toPlanetEntity() =
    PlanetEntity(
        id,
        name,
        rotationPeriod,
        orbitalPeriod,
        diameter,
        climate,
        gravity,
        terrain,
        surfaceWater,
        population,
        residents = Gson().toJson(residents),
        films = Gson().toJson(films),
        createdAt,
        url
    )

fun PlanetEntity.toPlanet() = Planet(
    id,
    name,
    rotationPeriod,
    orbitalPeriod,
    diameter,
    climate,
    gravity,
    terrain,
    surfaceWater,
    population,
    residents = Gson().fromJson(residents, object : TypeToken<List<String>>() {}.type),
    films = Gson().fromJson(films, object : TypeToken<List<String>>() {}.type),
    createdAt,
    url
)

