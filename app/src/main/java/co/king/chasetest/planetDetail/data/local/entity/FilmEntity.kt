package co.king.chasetest.planetDetail.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("film")
data class FilmEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val url: String,
    val planetId: Int
)
