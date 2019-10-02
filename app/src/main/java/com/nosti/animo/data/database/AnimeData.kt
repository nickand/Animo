package com.nosti.animo.data.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nosti.animo.data.AnimeAttributes
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class AnimeData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "anime_type") val type: String? = "",
    @Embedded var attributes: AnimeAttributes?,
    val favorite: Boolean
): Parcelable {
    constructor() : this(1, "", null, false)
}