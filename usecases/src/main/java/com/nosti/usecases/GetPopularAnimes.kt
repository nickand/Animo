package com.nosti.usecases

import com.nosti.data.AnimeRepository
import com.nosti.domain.AnimeData

class GetPopularAnimes(private val animeRepository: AnimeRepository) {

    suspend fun invoke(): List<AnimeData> {
        return animeRepository.getPopularAnimes()
    }

    suspend fun invokeFavorite(): List<AnimeData> {
        return animeRepository.getFavoriteAnimes()
    }

    suspend fun invokeSearch(postTitle: String): List<AnimeData> {
        return animeRepository.getSearchAnimes(postTitle, 20)
    }
}
