package com.example.animeapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.animeapp.model.local.data.Anime
import com.example.animeapp.model.local.dao.AnimeDao
import com.example.animeapp.model.local.entities.FavouritesEntity
import com.example.animeapp.model.local.entities.ToWatchEntity
import com.example.animeapp.model.remote.data.AnimeDTO
import com.example.animeapp.model.remote.service.AnimeService
import com.example.animeapp.utils.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val animeService: AnimeService,
    private val animeDao: AnimeDao) {

    suspend fun getAllAnimes() = Pager(
        PagingConfig(pageSize = 25, initialLoadSize = 50)) {
        AnimePagingSource(animeService)
    }.flow

    suspend fun searchAnime(query:String):List<Anime> {
        return animeService.findAnime(query)
            .data
            .map(AnimeDTO::toModel)
    }

    suspend fun getAnimeByID(animeID:Int) = animeService.getAnimeDetails(animeID).data.toModel()


    fun getFavouriteAnimeIDs():Flow<List<Int>> = animeDao.getAllFavouriteAnime()
        .map { listOfFavs->
            listOfFavs.map {
                it.animeID
            }
        }

    fun getToWatchAnimeIDs():Flow<List<Int>> = animeDao.getAllToWatchAnime()
        .map {listOfToWatch ->
            listOfToWatch.map {
                it.animeId
            }
        }
    suspend fun addAnimeToFav(animeId: Int) =
        animeDao.addAnimeToFavourites(FavouritesEntity(
            animeID = animeId
        ))

    suspend fun deleteAnimeFromFav(animeId: Int) =
        animeDao.deleteAnimeFromFavourites(FavouritesEntity(
            animeID = animeId
        ))

    suspend fun addAnimeToWatchlist(anime: Anime) =
        animeDao.addAnimeToWatch(
            ToWatchEntity(
            animeId = anime.malID,
            episodes = anime.episodes,
            score = anime.score,
            synopsis = anime.synopsis,
            title = anime.title,
            year = anime.year
        )
        )

    suspend fun deleteAnimeFromWatch(animeId:Int) =
        animeDao.deleteAnimeFromToWatch(animeId)
}