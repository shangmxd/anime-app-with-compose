package com.example.animeapp.usecase

import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.repository.MainRepository
import javax.inject.Inject

class UpdateSavedAnimeUseCase @Inject constructor(val mainRepository: MainRepository) {

    suspend operator fun invoke(anime: Anime, command:CMD) {
        when(command) {
            CMD.FAVOURITES -> {
                if(anime.isFavoured) mainRepository.deleteAnimeFromFav(anime.malID)
                else mainRepository.addAnimeToFav(anime.malID)
            }
            CMD.TO_WATCH -> {
                if(anime.isInWatch) mainRepository.deleteAnimeFromWatch(anime.malID)
                else mainRepository.addAnimeToWatchlist(anime)
            }
        }

    }

    enum class CMD {
        TO_WATCH,
        FAVOURITES
    }
}