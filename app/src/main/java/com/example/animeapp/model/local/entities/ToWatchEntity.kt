package com.example.animeapp.model.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("to_watch")
 class ToWatchEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val animeId:Int,
    @ColumnInfo("Title")
    val title:String,
    @ColumnInfo("Synopsis")
    val synopsis:String,
    @ColumnInfo("Score")
    val score:Double,
    @ColumnInfo("Year")
    val year:Int,
    @ColumnInfo("Episodes")
    val episodes:Int
)
