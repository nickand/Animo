package com.nosti.animo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nosti.animo.data.AnimeAttributes

@Database(entities = [AnimeData::class, AnimeAttributes::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context) = Room.databaseBuilder(
            context,
            AnimeDatabase::class.java,
            "animo-db"
        ).build()
    }

    abstract fun animeDao(): AnimeDAO
}