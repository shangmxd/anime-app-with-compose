package com.example.animeapp.di

import com.example.animeapp.model.remote.service.AnimeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RetrofitModule {
    companion object{
        val BASE_URL = "https://api.jikan.moe/v4/"
    }

    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getAnimeService(retrofit: Retrofit): AnimeService {
     return retrofit.create(AnimeService::class.java)
    }
}

