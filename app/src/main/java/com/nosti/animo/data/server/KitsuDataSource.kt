package com.nosti.animo.data.server

import com.nosti.animo.data.toDomainAnime
import com.nosti.data.RemoteDataSource
import com.nosti.domain.AnimeData

class KitsuDataSource : RemoteDataSource {

    override suspend fun getPopularAnimes(): List<AnimeData> =
        AnimoDb.service
            .getAnimeList().await()
            .data
            .map { it.toDomainAnime() }

    override suspend fun getPopularAnimes(page: Int): List<AnimeData> =
        AnimoDb.service
            .getAnimeList(page, 0).await()
            .data
            .map { it.toDomainAnime() }

    override suspend fun getSearchAnimes(animeTitle: String, limit: Int): List<AnimeData> =
        AnimoDb.service
            .getSearchAnime(animeTitle, limit).await()
            .data
            .map { it.toDomainAnime() }
}