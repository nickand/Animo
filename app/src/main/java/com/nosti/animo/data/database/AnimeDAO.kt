package com.nosti.animo.data.database

import androidx.room.*

@Dao
interface AnimeDAO {

    @Query("SELECT * FROM AnimeData")
    fun getAll(): List<AnimeData>

    @Query("SELECT * FROM AnimeData WHERE favorite = 1 ORDER BY id ASC")
    fun getAllFavorites(): List<AnimeData>

    @Query("SELECT * FROM AnimeData WHERE id = :id")
    fun findById(id: Int): AnimeData

    @Query("SELECT COUNT(id) FROM AnimeData")
    fun animeCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnimes(animes: List<AnimeData>)

    @Update
    fun updateAnime(anime: AnimeData)
}