package com.example.animeapp.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.usecase.GetAllAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverAnimeViewModel @Inject constructor(private val getAllAnimeUseCase: GetAllAnimeUseCase) :
    ViewModel() {

    private val _mangaFlow: MutableStateFlow<PagingData<Anime>> =
        MutableStateFlow(PagingData.empty())
    val mangaStateFlow: StateFlow<PagingData<Anime>> get() = _mangaFlow.asStateFlow()

    init {
        viewModelScope.launch {
                getAllAnimeUseCase()
                .cachedIn(viewModelScope)
                .collectLatest { _mangaFlow.emit(it) }
        }
    }
}