package co.king.chasetest.planetList.data.remote.dto

data class PlanetListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PlanetDto>
)
