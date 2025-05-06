package com.example.animeapp.usecase

import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.repository.MainRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetSavedAnimeUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {

    operator fun invoke(savedCommands: SavedCommands) =
        when (savedCommands) {
            SavedCommands.FAVOURITES -> {
                getFavourites()
            }

            SavedCommands.TO_WATCH -> {
                mainRepository.getToWatchAnimeIDsInAnimeModel()
            }
        }


    private fun getFavourites(): Flow<List<Anime>> = mainRepository.getFavouriteAnimeIDs()
        .map { list ->
            coroutineScope {
                list.map { animeId ->
                    async {
                        mainRepository.getAnimeByID(animeId)
                    }
                }.awaitAll()
            }
        }

    enum class SavedCommands {
        FAVOURITES,
        TO_WATCH
    }

}