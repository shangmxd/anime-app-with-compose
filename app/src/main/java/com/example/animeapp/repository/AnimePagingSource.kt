package com.example.animeapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.model.remote.service.AnimeService
import com.example.animeapp.utils.toModel

class AnimePagingSource(private val animeService: AnimeService) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        try {
            val pageNo = params.key ?: 1
            val response = animeService.getAllAnime(pageNo)
            return LoadResult.Page(
                data = response.data
                    .map { it.toModel() },
                prevKey = if (pageNo == 1) null else pageNo.minus(1),
                nextKey = if (pageNo == response.data.size) null else pageNo.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        val lastPosition = state.anchorPosition
        if(lastPosition!=null) {
            val pagePosition = state.closestPageToPosition(lastPosition)
            if(pagePosition!=null) {
                return pagePosition.nextKey?.minus(1)?: pagePosition.prevKey?.plus(1)
            }
        }
        return null
    }
}