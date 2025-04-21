package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class GenresDTO(
    @SerializedName("name") val genreName: String,
)
