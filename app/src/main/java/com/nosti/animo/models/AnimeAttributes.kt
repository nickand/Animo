package com.nosti.animo.models

import android.os.Parcel
import android.os.Parcelable

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
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            TODO("coverImage"),
            TODO("posterImage"))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(createdAt)
        parcel.writeString(synopsis)
        parcel.writeString(canonicalTitle)
        parcel.writeString(averageRating)
        parcel.writeString(startDate)
        parcel.writeString(ageRatingGuide)
        parcel.writeString(endDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AnimeAttributes> {
        override fun createFromParcel(parcel: Parcel): AnimeAttributes {
            return AnimeAttributes(parcel)
        }

        override fun newArray(size: Int): Array<AnimeAttributes?> {
            return arrayOfNulls(size)
        }
    }
}