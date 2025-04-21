package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class AnimeDetailResponseDTO(
    @SerializedName("data") val data:AnimeDTO
)
