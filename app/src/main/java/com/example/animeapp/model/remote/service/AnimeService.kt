package com.example.animeapp.model.remote.service

import com.example.animeapp.model.remote.data.AnimeDTO
import com.example.animeapp.model.remote.data.AnimeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeService {

    @GET("anime")
    suspend fun getAllAnime(
        @Query("page") page:Int
    ): AnimeResponse
}