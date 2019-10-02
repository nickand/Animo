package com.nosti.usecases

import com.nosti.data.AnimeRepository
import com.nosti.domain.AnimeData

class FindAnimeById(private val animeRepository: AnimeRepository) {
    suspend fun invoke(id: Int): AnimeData = animeRepository.findById(id)
}