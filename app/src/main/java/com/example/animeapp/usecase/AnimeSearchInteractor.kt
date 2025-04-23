package com.example.animeapp.usecase

import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.repository.MainRepository
import com.example.animeapp.utils.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AnimeSearchInteractor @Inject constructor(private val mainRepository: MainRepository) {


    companion object {
        const val DEBOUNCE_LIMIT = 300L
    }

    private val _searchQuery = MutableSharedFlow<CharSequence>(
        extraBufferCapacity = 1
    )

    val searchQuery: Flow<UiState<List<Anime>>>
        get() = _searchQuery
            .debounce(DEBOUNCE_LIMIT)
            .map(CharSequence::toString)
            .map(::performSearchQuery)
            .catch { emit(UiState.Error) }


    fun searchAnimeByQuery(query: CharSequence) {
        _searchQuery.tryEmit(query)
    }

    suspend fun performSearchQuery(query: String): UiState<List<Anime>> {
        try {
            val result = mainRepository.searchAnime(query)
            return UiState.Result(result)
        } catch (e: Exception) {
            return UiState.Error
        }
    }

}