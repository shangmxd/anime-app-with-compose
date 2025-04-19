package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class JpgDTO(
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("large_image_url") val largeImageUrl: String,
    @SerializedName("small_image_url") val smallImageUrl: String
)