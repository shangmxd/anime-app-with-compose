package com.example.animeapp.usecase

import com.example.animeapp.model.local.Anime
import com.example.animeapp.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
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

    val searchQuery: Flow<List<Anime>>
        get() = _searchQuery
            .debounce(DEBOUNCE_LIMIT)
            .map(CharSequence::toString)
            .map(::performSearchQuery)


    fun searchAnimeByQuery(query:CharSequence) {
        _searchQuery.tryEmit(query)
    }

    suspend fun performSearchQuery(query:String):List<Anime> {
        return mainRepository.searchAnime(query)
    }

}