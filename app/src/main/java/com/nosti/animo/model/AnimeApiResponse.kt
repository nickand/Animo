package com.nosti.animo.model

import android.os.Parcelable
import androidx.room.*
import com.nosti.animo.model.database.Anime
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

//region Anime POJO
@Parcelize
@Entity
data class AnimeData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "anime_type") var type: String,
    var attributes: AnimeAttributes?
): Parcelable {
    constructor() : this(1, "", null)
}

interface AnimeApiResponse {

    @GET("api/edge/anime")
    fun getAnimeList(): Deferred<Anime>

}