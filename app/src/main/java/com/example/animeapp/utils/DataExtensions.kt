package com.example.animeapp.utils

import androidx.room.ColumnInfo
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.model.local.data.Images
import com.example.animeapp.model.local.data.Jpg
import com.example.animeapp.model.local.data.Licensor
import com.example.animeapp.model.local.entities.ToWatchEntity
import com.example.animeapp.model.remote.data.AnimeDTO
import com.example.animeapp.model.remote.data.ImagesDTO
import com.example.animeapp.model.remote.data.JpgDTO
import com.example.animeapp.model.remote.data.LicensorDTO

fun ToWatchEntity.toAnime() = Anime(
    duration = "0",
    episodes = this.episodes,
    images = Images(Jpg(this.backgroundImg,null,null)),
    licensors = emptyList(),
    malID = this.animeId,
    score = this.score,
    synopsis = this.synopsis?:"No information available",
    title = this.title,
    year = this.year,
    genre = null
)



fun AnimeDTO.toModel() = Anime(
    duration = this.duration,
    episodes = this.episodes,
    images = this.images.toLocalImage(),
    licensors = this.licensors.map {
        it.toLocalLicensors()
    },
    malID = this.malID,
    score = this.score,
    synopsis = this.synopsis?:"No information available",
    title = this.title,
    year = this.year,
    genre = this.genres.map {
        it.genreName
    }.joinToString(" , ")
)

fun ImagesDTO.toLocalImage() = Images(
    jpg = this.jpg.toLocalJpg()
)

fun JpgDTO.toLocalJpg() = Jpg(
    imageUrl = this.imageUrl,
    smallImageUrl = this.smallImageUrl,
    largeImageUrl = this.largeImageUrl
)

fun LicensorDTO.toLocalLicensors() = Licensor(
    malId = this.malId,
    name = this.name,
    type = this.type,
    url = this.url
)
