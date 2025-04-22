package com.example.animeapp.utils

sealed class UiState<out T: Any>{
    data object Loading:UiState<Nothing>()
    class Result<out T: Any> (val result: T):UiState<T>()
    data object Error :UiState<Nothing>()
    
}
