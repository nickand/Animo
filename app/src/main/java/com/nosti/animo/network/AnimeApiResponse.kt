package com.nosti.animo.network

import com.nosti.animo.models.Anime
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface AnimeApiResponse {

    @GET("api/edge/anime")
    fun getAnimeList(): Deferred<Anime>

}