package com.nosti.data

import com.nosti.domain.AnimeData

interface LocalDataSource {
    suspend fun isEmpty(): Boolean
    suspend fun saveAnimes(animes: List<AnimeData>)
    suspend fun getPopularAnimes(): List<AnimeData>
    suspend fun getFavoriteAnimes(): List<AnimeData>
    suspend fun findById(id: Int): AnimeData
    suspend fun update(anime: AnimeData)
}