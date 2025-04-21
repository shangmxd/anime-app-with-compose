package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class AnimeResponseDTO(
    @SerializedName("data") val data: List<AnimeDTO>
)