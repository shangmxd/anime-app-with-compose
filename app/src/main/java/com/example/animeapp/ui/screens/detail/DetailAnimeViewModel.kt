package com.example.animeapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.usecase.GetAnimeDetailsUseCase
import com.example.animeapp.usecase.UpdateSavedAnimeUseCase
import com.example.animeapp.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailAnimeViewModel @Inject constructor(
    state: SavedStateHandle,
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase,
    private val updateSavedAnimeUseCase: UpdateSavedAnimeUseCase
):ViewModel() {

    private val animeId = state.get<Int>("id")?:0
    private val _animeDetails:MutableStateFlow<UiState<Anime>> = MutableStateFlow(UiState.Loading)
    val animeDetails:StateFlow<UiState<Anime>> get() = _animeDetails.asStateFlow()

    init {
        loadAnimeDetails()
    }

    private fun loadAnimeDetails() {
        viewModelScope.launch {
            getAnimeDetailsUseCase(animeId = animeId)
                .collect(_animeDetails)
        }
    }

    fun onFavButtonClicked(){
        updateSaveAnimeState(UpdateSavedAnimeUseCase.CMD.FAVOURITES)
    }

    fun onToWatchButtonClicked(){
        updateSaveAnimeState(UpdateSavedAnimeUseCase.CMD.TO_WATCH)
    }

    private fun updateSaveAnimeState(btnCommand:UpdateSavedAnimeUseCase.CMD) = viewModelScope.launch(Dispatchers.IO) {
        val currentValue = animeDetails.value
        if(currentValue is UiState.Result) {
            updateSavedAnimeUseCase(currentValue.result,btnCommand)
        }
    }

}