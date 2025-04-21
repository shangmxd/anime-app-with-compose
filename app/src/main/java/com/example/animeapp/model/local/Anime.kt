package com.example.animeapp.model.local

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
    val genre:String
)
