package com.nosti.animo.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//region Anime POJO
@Parcelize
data class AnimeData(
    var id: Int,
    var type: String,
    var attributes: AnimeAttributes): Parcelable