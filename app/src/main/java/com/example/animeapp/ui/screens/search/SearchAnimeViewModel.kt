package com.example.animeapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.model.local.Anime
import com.example.animeapp.usecase.AnimeSearchInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchAnimeViewModel @Inject constructor(private val animeSearchInteractor: AnimeSearchInteractor): ViewModel() {

    private val _searchedAnime:MutableStateFlow<List<Anime>> = MutableStateFlow(emptyList())

    val searchedAnime:StateFlow<List<Anime>>
        get() = _searchedAnime

    init {
        viewModelScope.launch {
            animeSearchInteractor.searchQuery
                .collect(_searchedAnime)
        }
    }

    fun onSearchQueryChanged(query:CharSequence) = animeSearchInteractor.searchAnimeByQuery(query)
}