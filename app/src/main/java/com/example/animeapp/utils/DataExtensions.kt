package com.example.animeapp.utils

import com.example.animeapp.model.local.Anime
import com.example.animeapp.model.local.Images
import com.example.animeapp.model.local.Jpg
import com.example.animeapp.model.local.Licensor
import com.example.animeapp.model.remote.data.AnimeDTO
import com.example.animeapp.model.remote.data.ImagesDTO
import com.example.animeapp.model.remote.data.JpgDTO
import com.example.animeapp.model.remote.data.LicensorDTO

fun AnimeDTO.toModel() = Anime(
    duration = this.duration,
    episodes = this.episodes,
    images = this.images.toLocalImage(),
    licensors = this.licensors.map {
        it.toLocalLicensors()
    },
    malID = this.malID,
    score = this.score,
    synopsis = this.synopsis,
    title = this.title,
    year = this.year
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
