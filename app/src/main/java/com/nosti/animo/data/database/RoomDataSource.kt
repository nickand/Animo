package com.nosti.animo.data.database

import com.nosti.animo.data.toDomainAnime
import com.nosti.animo.data.toRoomAnime
import com.nosti.data.LocalDataSource
import com.nosti.domain.AnimeData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomDataSource (db: AnimeDatabase) : LocalDataSource {

    private val animeDao = db.animeDao()

    override suspend fun isEmpty(): Boolean =
        withContext(Dispatchers.IO) { animeDao.animeCount() <= 0 }

    override suspend fun saveAnimes(animes: List<AnimeData>) {
        withContext(Dispatchers.IO) { animeDao.insertAnimes(animes.map { it.toRoomAnime() }) }
    }

    override suspend fun getPopularAnimes(): List<AnimeData> = withContext(Dispatchers.IO) {
        animeDao.getAll().map { it.toDomainAnime() }
    }

    override suspend fun getFavoriteAnimes(): List<AnimeData> = withContext(Dispatchers.IO) {
        animeDao.getAllFavorites().map { it.toDomainAnime() }
    }

    override suspend fun findById(id: Int): AnimeData = withContext(Dispatchers.IO) {
        animeDao.findById(id).toDomainAnime()
    }

    override suspend fun update(anime: AnimeData) {
        withContext(Dispatchers.IO) { animeDao.updateAnime(anime.toRoomAnime()) }
    }
}