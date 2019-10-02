package com.nosti.animo.data.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface AnimeDbService {
    @GET("api/edge/anime")
    fun getAnimeList(): Deferred<AnimeDbResult>

    @GET("api/edge/anime")
    fun getAnimeList(@Query("page[limit]") page: Int, @Query("page[offset]") perPage: Int): Deferred<AnimeDbResult>

    @GET("api/edge/anime")
    fun getSearchAnime(@Query("filter[text]") animeTitle: String, @Query("page[limit]") limit: Int): Deferred<AnimeDbResult>

}