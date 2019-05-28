package com.nosti.animo.model.server

import com.nosti.animo.model.server.AnimoDb

class AnimoRepository {

    suspend fun getAnimes() =
        AnimoDb.service.getAnimeList()
            .await()
}