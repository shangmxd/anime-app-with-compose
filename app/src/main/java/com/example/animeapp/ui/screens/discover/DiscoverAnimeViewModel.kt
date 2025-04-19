package com.example.animeapp.ui.screens.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.model.local.Anime
import com.example.animeapp.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverAnimeViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {

    private val _stateFlow = MutableStateFlow("Initial Value")
    val stateFlow: StateFlow<String> get() = _stateFlow.asStateFlow()

    private val _mangaFlow: MutableStateFlow<List<Anime>> = MutableStateFlow(emptyList())
    val mangaStateFlow: StateFlow<List<Anime>> get() = _mangaFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _mangaFlow.emit(mainRepository.getAllAnimes())
        }
    }
}