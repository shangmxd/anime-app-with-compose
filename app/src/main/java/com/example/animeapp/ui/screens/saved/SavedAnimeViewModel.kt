package com.example.animeapp.ui.screens.saved

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.usecase.GetSavedAnimeUseCase
import com.example.animeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SavedAnimeViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getSavedAnimeUseCase: GetSavedAnimeUseCase
) :ViewModel() {

    private val savedScreenCommand= state.get<GetSavedAnimeUseCase.SavedCommands>("saveType")
        ?: GetSavedAnimeUseCase.SavedCommands.TO_WATCH

    val savedAnime
        get() = getSavedAnimeUseCase(savedScreenCommand)
}