package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class AnimeResponse(
    @SerializedName("data") val data: List<AnimeDTO>
)