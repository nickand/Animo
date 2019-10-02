package com.nosti.usecases

import com.nosti.data.AnimeRepository
import com.nosti.domain.AnimeData

class ToggleAnimeFavorite(private val animeRepository: AnimeRepository) {
    suspend fun invoke(movie: AnimeData): AnimeData = with(movie) {
        copy(favorite = !favorite).also { animeRepository.update(it) }
    }
}