package com.example.animeapp.model.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.animeapp.model.local.entities.FavouritesEntity
import com.example.animeapp.model.local.entities.ToWatchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM favourites")
    fun getAllFavouriteAnime(): Flow<List<FavouritesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnimeToFavourites(favAnimeEntity: FavouritesEntity)

    @Delete
    fun deleteAnimeFromFavourites(favAnimeEntity: FavouritesEntity)

    @Query("SELECT * FROM to_watch")
    fun getAllToWatchAnime(): Flow<List<ToWatchEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAnimeToWatch(watchAnimeEntity: ToWatchEntity)

    @Query("DELETE FROM to_watch WHERE id = :animeId")
    fun deleteAnimeFromToWatch(animeId: Int)

}