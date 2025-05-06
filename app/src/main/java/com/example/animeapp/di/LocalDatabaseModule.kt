package com.example.animeapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.animeapp.model.local.database.AnimeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDatabaseModule {

    @Singleton
    @Provides
    fun provideAnimeDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, AnimeDatabase::class.java, "anime_database")
        .fallbackToDestructiveMigration()
        .build()
        .animeDao()
}