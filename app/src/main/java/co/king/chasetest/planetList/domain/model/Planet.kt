package co.king.chasetest.planetList.domain.model

data class Planet(
    val id: Int,
    val name: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val diameter: String,
    val climate: String,
    val gravity: String,
    val terrain: String,
    val surfaceWater: String,
    val population: String,
    val residentsCount: Int,
    val filmsCount: Int,
    val createdAt: String,
    val url: String
)