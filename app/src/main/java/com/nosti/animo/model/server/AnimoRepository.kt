package com.nosti.animo.model.server

import com.nosti.animo.model.AnimeData
import com.nosti.animo.ui.AnimoApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.nosti.animo.model.AnimeData as ServerAnime

class AnimoRepository(application: AnimoApp) {

    private val db = application.db

    suspend fun findPopularAnimes(): List<AnimeData> = withContext(Dispatchers.IO) {

        with(db.animeDao()) {
            if (animeCount() <= 0) {

                val animes = AnimoDb.service
                    .getAnimeList()
                    .await()
                    .data

                insertAnimes(animes.map(ServerAnime::convertToDbAnime))
            }

            getAll()
        }
    }

    suspend fun findById(id: Int): AnimeData = withContext(Dispatchers.IO) {
        db.animeDao().findById(id)
    }

    suspend fun update(anime: AnimeData) = withContext(Dispatchers.IO) {
        db.animeDao().updateAnime(anime)
    }
}

private fun ServerAnime.convertToDbAnime() = AnimeData(
    0,
    type,
    attributes,
    false
)