package com.example.animeapp.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animeapp.model.local.dao.AnimeDao
import com.example.animeapp.model.local.entities.FavouritesEntity
import com.example.animeapp.model.local.entities.ToWatchEntity

@Database(entities = [FavouritesEntity::class,ToWatchEntity::class], version = 1)
abstract class AnimeDatabase:RoomDatabase() {

    abstract fun animeDao():AnimeDao

}