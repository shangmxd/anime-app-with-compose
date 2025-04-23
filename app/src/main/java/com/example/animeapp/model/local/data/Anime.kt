package com.example.animeapp.model.local.data

data class Anime(
    val duration: String,
    val episodes: Int,
    val images: Images,
    val licensors: List<Licensor>,
    val malID: Int,
    val score: Double,
    val synopsis: String,
    val title: String,
    val year: Int,
    val genre:String,
    val isFavoured:Boolean = false,
    val isInWatch:Boolean = false
)
