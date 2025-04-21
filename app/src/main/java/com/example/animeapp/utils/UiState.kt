package com.example.animeapp.utils

import com.example.animeapp.model.local.Anime

sealed class UiState{
    data object Loading:UiState()
    class Result(val result: Anime):UiState()
    class Error(val error:Throwable):UiState()


}
