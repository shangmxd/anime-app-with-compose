package com.example.animeapp.model.remote.data

import com.google.gson.annotations.SerializedName

data class ImagesDTO(
    @SerializedName("jpg") val jpg: JpgDTO,
)