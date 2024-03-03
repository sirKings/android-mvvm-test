package co.king.chasetest.planetList.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("planet")
data class PlanetEntity(
    @PrimaryKey
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
    val residents: String,
    val films: String,
    val createdAt: String,
    val url: String
)