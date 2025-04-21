package com.example.animeapp.model.remote.service

import com.example.animeapp.model.remote.data.AnimeDetailResponseDTO
import com.example.animeapp.model.remote.data.AnimeResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("anime")
    suspend fun getAllAnime(
        @Query("page") page:Int
    ): AnimeResponseDTO

    @GET("anime")
    suspend fun findAnime(
        @Query("q") query:String
    ):AnimeResponseDTO

    @GET("anime/{id}/full")
    suspend fun getAnimeDetails(
        @Path("id") animeId:Int
    ):AnimeDetailResponseDTO
}