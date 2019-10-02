package com.nosti.animo.data

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

//region Anime POJO
@Parcelize
@Entity
data class AnimeData(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name = "anime_type") var type: String,
    @Embedded var attributes: AnimeAttributes?,
    val favorite: Boolean
): Parcelable {
    constructor() : this(1, "", null, false)
}