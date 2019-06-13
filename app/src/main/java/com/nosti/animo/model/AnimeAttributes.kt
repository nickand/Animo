package com.nosti.animo.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class AnimeAttributes(
    @PrimaryKey(autoGenerate = true) var attributeId: Int,
    var createdAt: String,
    var synopsis: String,
    var canonicalTitle: String,
    var averageRating: String,
    var startDate: String,
    var ageRatingGuide: String,
    var endDate: String,
    @Embedded var coverImage: AnimeCoverImage?,
    @Embedded var posterImage: AnimePosterImage?
) : Parcelable {
    constructor() : this(1,"","","","","","","",null, null)
}