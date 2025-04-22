package com.example.animeapp.usecase

import com.example.animeapp.repository.MainRepository
import com.example.animeapp.utils.UiState
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAnimeDetailsUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke(animeId: Int) = flow {
        emit(UiState.Loading)
        val result = mainRepository.getAnimeByID(animeId)
        emit(UiState.Result(result))
        }.catch {
            emit(UiState.Error)
    }
}