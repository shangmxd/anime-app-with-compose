package com.example.animeapp.di.modules

import com.example.animeapp.model.remote.service.AnimeService
import com.example.animeapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getMainRepository(animeService: AnimeService) = MainRepository(animeService)
}