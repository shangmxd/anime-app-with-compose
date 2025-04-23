package com.example.animeapp.usecase

import com.example.animeapp.repository.MainRepository
import com.example.animeapp.utils.UiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(animeId: Int) = flow {
        emit(UiState.Loading)
        val result = mainRepository.getAnimeByID(animeId)
        mainRepository.getFavouriteAnimeIDs()
            .combine(mainRepository.getToWatchAnimeIDs()) { favIds, toWatchIds ->
                val isFavourite = favIds.any { favId ->
                    favId == animeId
                }
                val isToWatch = toWatchIds.any { toWatchId ->
                    toWatchId == animeId
                }
                return@combine UiState.Result(
                    result.copy(
                        isFavoured = isFavourite,
                        isInWatch = isToWatch
                    )
                )
            }.collect(::emit)

    }.catch {
        emit(UiState.Error)
    }
}