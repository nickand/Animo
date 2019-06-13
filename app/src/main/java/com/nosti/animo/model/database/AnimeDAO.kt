package com.nosti.animo.model.database

import androidx.room.*
import com.nosti.animo.model.AnimeData

@Dao
interface AnimeDAO {

    @Query("SELECT * FROM AnimeData")
    fun getAll(): List<AnimeData>

    @Query("SELECT * FROM AnimeData WHERE id = :id")
    fun findById(id: Int): AnimeData

    @Query("SELECT COUNT(id) FROM AnimeData")
    fun animeCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAnimes(animes: List<AnimeData>)

    @Update
    fun updateAnime(movie: AnimeData)
}