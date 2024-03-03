package co.king.chasetest.planetDetail.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("resident")
data class ResidentEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val height: String,
    val mass: String,
    val hairColor: String,
    val skinColor: String,
    val eyeColor: String,
    val birthYear: String,
    val gender: String,
    val url: String,
    val planetId: Int
)
