package com.nosti.data

import com.nosti.domain.AnimeData

interface RemoteDataSource {
    suspend fun getPopularAnimes(): List<AnimeData>
    suspend fun getSearchAnimes(animeTitle: String, limit: Int): List<AnimeData>
    suspend fun getPopularAnimes(page: Int): List<AnimeData>
}