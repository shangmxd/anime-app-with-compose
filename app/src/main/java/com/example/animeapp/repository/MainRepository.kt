package com.example.animeapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.animeapp.model.local.Anime
import com.example.animeapp.model.remote.service.AnimeService
import com.example.animeapp.utils.toModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepository @Inject constructor(private val animeService: AnimeService) {

    suspend fun getAllAnimes() = Pager(
        PagingConfig(pageSize = 25, initialLoadSize = 50)) {
        AnimePagingSource(animeService)
    }.flow

}