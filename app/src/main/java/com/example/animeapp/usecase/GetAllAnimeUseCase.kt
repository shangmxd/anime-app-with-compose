package com.example.animeapp.usecase

import com.example.animeapp.repository.MainRepository
import javax.inject.Inject

class GetAllAnimeUseCase @Inject constructor(private val mainRepository: MainRepository) {

    suspend operator fun invoke() = mainRepository.getAllAnimes()
}