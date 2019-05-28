package com.nosti.animo.model.database

import androidx.room.TypeConverter
import com.nosti.animo.model.AnimeAttributes

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun toAnimeAttribute(value: AnimeAttributes): String {
            return value.toString()
        }

        @TypeConverter
        @JvmStatic
        fun fromAnimeAttribute(animeAttributes: String): AnimeAttributes {
            return AnimeAttributes(1, animeAttributes, animeAttributes, animeAttributes, animeAttributes, animeAttributes, animeAttributes, animeAttributes,null, null)
        }
    }
}