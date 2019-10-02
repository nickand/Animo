package com.nosti.data

import com.nosti.domain.AnimeData

class AnimeRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {

    suspend fun getPopularAnimes(): List<AnimeData> {
        val animes =
            remoteDataSource.getPopularAnimes(20)
        localDataSource.saveAnimes(animes)

        return animes
    }

    suspend fun getFavoriteAnimes(): List<AnimeData> {
        if (localDataSource.isEmpty()) {
            val animes =
                remoteDataSource.getPopularAnimes()
            localDataSource.saveAnimes(animes)
        }

        return localDataSource.getFavoriteAnimes()
    }

    suspend fun getSearchAnimes(animeTitle: String, limit: Int): List<AnimeData> {
        val animes =
            remoteDataSource.getSearchAnimes(animeTitle, limit)
        localDataSource.saveAnimes(animes)

        return animes
    }

    suspend fun findById(id: Int): AnimeData = localDataSource.findById(id)
    suspend fun update(movie: AnimeData) = localDataSource.update(movie)
}