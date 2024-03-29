package co.king.chasetest.planetDetail.data.mapper

import co.king.chasetest.planetDetail.data.local.entity.FilmEntity
import co.king.chasetest.planetDetail.data.local.entity.ResidentEntity
import co.king.chasetest.planetDetail.data.remote.dto.FilmDto
import co.king.chasetest.planetDetail.data.remote.dto.ResidentDto
import co.king.chasetest.planetDetail.domain.model.Film
import co.king.chasetest.planetDetail.domain.model.Resident
import co.king.chasetest.util.getIdFromUrl

fun FilmDto.toFilm() =
    Film(
        id = getIdFromUrl(url),
        title,
        episodeId = episode_id,
        openingCrawl = opening_crawl,
        director,
        producer,
        releaseDate = release_date,
        url
    )


fun ResidentDto.toResident() =
    Resident(
        id = getIdFromUrl(url),
        name,
        height,
        mass,
        hairColor = hair_color,
        skinColor = skin_color,
        eyeColor = eye_color,
        birthYear = birth_year,
        gender,
        url
    )

fun FilmDto.toFilmEntity(planetId: Int) =
    FilmEntity(
        id = getIdFromUrl(url),
        title,
        episodeId = episode_id,
        openingCrawl = opening_crawl,
        director,
        producer,
        releaseDate = release_date,
        url,
        planetId
)

fun FilmEntity.toFilm() = Film(
    id, title, episodeId, openingCrawl, director, producer, releaseDate, url
)

fun ResidentEntity.toResident() = Resident(
    id, name, height, mass, hairColor, skinColor, eyeColor, birthYear, gender, url
)

fun ResidentDto.toResidentEntity(planetId: Int) = ResidentEntity(
    id = getIdFromUrl(url),
    name,
    height,
    mass,
    hairColor = hair_color,
    skinColor = skin_color,
    eyeColor = eye_color,
    birthYear = birth_year,
    gender,
    url,
    planetId
)

