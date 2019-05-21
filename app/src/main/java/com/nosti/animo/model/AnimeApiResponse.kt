package com.nosti.animo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

//region Anime POJO
@Parcelize
data class AnimeData(
    var id: Int,
    var type: String,
    var attributes: AnimeAttributes
): Parcelable

interface AnimeApiResponse {

    @GET("api/edge/anime")
    fun getAnimeList(): Deferred<Anime>

}