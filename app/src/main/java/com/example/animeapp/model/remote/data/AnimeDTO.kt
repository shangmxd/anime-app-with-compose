package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class AnimeDTO(
    @SerializedName("duration") val duration: String,
    @SerializedName("episodes") val episodes: Int,
    @SerializedName("images") val images: ImagesDTO,
    @SerializedName("licensors") val licensors: List<LicensorDTO>,
    @SerializedName("mal_id") val malID: Int,
    @SerializedName("score") val score: Double,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("title") val title: String,
    @SerializedName("year") val year: Int
)