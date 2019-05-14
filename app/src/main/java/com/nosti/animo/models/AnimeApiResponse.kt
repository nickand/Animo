package com.nosti.animo.models

import android.os.Parcel
import android.os.Parcelable

//region Anime POJO
data class AnimeData(
    var id: Int,
    var type: String,
    var attributes: AnimeAttributes): Parcelable {
    constructor(source: Parcel): this(
        source.readInt(),
        source.readString(),
        source.readParcelable<AnimeAttributes>(AnimeAttributes::class.java.classLoader)
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(type)
        writeParcelable(attributes, 0)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<AnimeData> = object: Parcelable.Creator<AnimeData> {
            override fun createFromParcel(source: Parcel): AnimeData = AnimeData(source)
            override fun newArray(size: Int): Array<AnimeData?> = arrayOfNulls(size)
        }
    }
}