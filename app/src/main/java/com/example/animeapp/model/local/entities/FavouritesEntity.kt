package com.example.animeapp.model.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("favourites")
class FavouritesEntity(
    @PrimaryKey
    val animeID:Int
)
