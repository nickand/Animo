package com.nosti.animo.model

class AnimoRepository {

    suspend fun getAnimes() =
        AnimoDb.service.getAnimeList()
            .await()
}