package com.example.animeapp.model.remote.service

import com.example.animeapp.model.remote.data.AnimeDTO
import com.example.animeapp.model.remote.data.AnimeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface AnimeService {

    @GET("anime")
    suspend fun getAllAnime(): AnimeResponse
}