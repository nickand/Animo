package com.nosti.animo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AnimeAttributes(
    var createdAt: String,
    var synopsis: String,
    var canonicalTitle: String,
    var averageRating: String,
    var startDate: String,
    var ageRatingGuide: String,
    var endDate: String,
    var coverImage: AnimeCoverImage?,
    var posterImage: AnimePosterImage?
) : Parcelable