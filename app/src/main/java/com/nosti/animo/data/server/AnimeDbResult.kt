package com.nosti.animo.data.server

import android.os.Parcelable
import com.nosti.animo.data.AnimeAttributes
import kotlinx.android.parcel.Parcelize

data class AnimeDbResult(var data: List<AnimeData>)

@Parcelize
data class AnimeData(
    val id: Int,
    val type: String?,
    val attributes: AnimeAttributes?,
    val favorite: Boolean
): Parcelable