package com.example.animeapp.di

import com.example.animeapp.model.local.dao.AnimeDao
import com.example.animeapp.model.remote.service.AnimeService
import com.example.animeapp.repository.MainRepository
import com.example.animeapp.usecase.AnimeSearchInteractor
import com.example.animeapp.usecase.GetAllAnimeUseCase
import com.example.animeapp.usecase.GetSavedAnimeUseCase
import com.example.animeapp.usecase.UpdateSavedAnimeUseCase
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
    fun getMainRepository(animeService: AnimeService,animeDao: AnimeDao) =
        MainRepository(animeService,animeDao)

    @Singleton
    @Provides
    fun provideAllAnimeUseCase(mainRepository: MainRepository):GetAllAnimeUseCase{
        return GetAllAnimeUseCase(mainRepository)
    }

    @Singleton
    @Provides
    fun provideAnimeSearchInteractor(mainRepository: MainRepository):AnimeSearchInteractor{
        return AnimeSearchInteractor(mainRepository)
    }

    @Singleton
    @Provides
    fun provideUpdateSavedAnimeUseCase(mainRepository: MainRepository):UpdateSavedAnimeUseCase{
        return UpdateSavedAnimeUseCase(mainRepository)
    }
    @Singleton
    @Provides
    fun provideGetSavedAnimeUseCase(mainRepository: MainRepository):GetSavedAnimeUseCase{
        return GetSavedAnimeUseCase(mainRepository)
    }

}