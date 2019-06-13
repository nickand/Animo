package com.nosti.animo.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nosti.animo.model.AnimeAttributes
import com.nosti.animo.model.AnimeData

@Database(entities = [AnimeData::class, AnimeAttributes::class], version = 1)
abstract class AnimeDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDAO
}