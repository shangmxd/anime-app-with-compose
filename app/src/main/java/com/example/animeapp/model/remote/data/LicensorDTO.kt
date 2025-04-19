package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class LicensorDTO(
    @SerializedName("mal_id") val malId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("url") val url: String
)