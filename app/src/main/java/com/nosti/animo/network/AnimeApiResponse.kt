package com.nosti.animo.network

import com.nosti.animo.models.Anime
import retrofit2.Call
import retrofit2.http.GET

interface AnimeApiResponse {

    @get:GET("api/edge/anime")
    val animeList: Call<Anime>

}