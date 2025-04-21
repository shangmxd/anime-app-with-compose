package com.example.animeapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.model.local.Anime
import com.example.animeapp.usecase.GetAnimeDetailsUseCase
import com.example.animeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
):ViewModel() {

    private val animeId = state.get<Int>("id")?:0
    private val _animeDetails:MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val animeDetails:StateFlow<UiState> get() = _animeDetails.asStateFlow()

    init {
        loadAnimeDetails()
    }

    private fun loadAnimeDetails() {
        viewModelScope.launch {
            getAnimeDetailsUseCase.invoke(animeId = animeId)
                .collect(_animeDetails)
        }
    }

}